package com.foobar.now

import akka.http.scaladsl.Http
import cats.effect._
import cats.syntax.all._
import com.foobar.now.configuration.AppConfig
import com.foobar.now.dao.{ChallengeDao, ChallengeTypeDao, UserDao}
import com.foobar.now.database.DatabaseTransactor
import com.foobar.now.rest.routes.{ChallengeController, ChallengeTypeController, UserController}
import com.foobar.now.rest.{HttpServer, PublicApiEndpoint}
import com.foobar.now.service.{ChallengeService, ChallengeTypeService, UserService}
import com.typesafe.scalalogging.LazyLogging
import doobie.hikari.HikariTransactor
import monix.eval._

object WebServer extends TaskApp with LazyLogging {

  def run(args: List[String]): Task[ExitCode] = {
    (for {
      config <- Resource.liftF(AppConfig())
      xa <- DatabaseTransactor(config.database)
      _ <- initRestService(config, xa)
    } yield ())
      .use(_ => Task.never.as(ExitCode.Success))
      .onErrorHandle { ex =>
        logger.error("Error occurred during execution", ex)
        ExitCode.Error
      }
  }

  private def initRestService(config: AppConfig,
                              xa: HikariTransactor[Task]): Resource[Task, Http.ServerBinding] = {
    val controllers = Seq(
      new UserController(new UserService(new UserDao, xa)),
      new ChallengeController(config.http, new ChallengeService(new ChallengeDao(), xa)),
      new ChallengeTypeController(config.http, new ChallengeTypeService(new ChallengeTypeDao(), xa))
    )

    val routes = new PublicApiEndpoint(controllers).route
    Resource.liftF(HttpServer(config.http, routes))
  }

}

