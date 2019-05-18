package com.foobar.now

import akka.http.scaladsl.Http
import cats.effect._
import com.foobar.now.configuration.AppConfig
import com.foobar.now.dao.{ChallengeDao, ChallengeTypeDao, UserDao}
import com.foobar.now.database.DatabaseTransactor
import com.foobar.now.rest.routes.{ChallengeController, ChallengeTypeController, UserController}
import com.foobar.now.rest.{HttpServer, PublicApiEndpoint}
import com.foobar.now.service.{ChallengeService, ChallengeTypeService, UserService}
import com.typesafe.scalalogging.LazyLogging
import doobie.hikari.HikariTransactor
import monix.eval._
import monix.execution.Scheduler.Implicits.global

object WebServer extends TaskApp with LazyLogging {

  def run(args: List[String]): Task[ExitCode] = {

    val main = for {
      config <- AppConfig()
      _ <- DatabaseTransactor(config.database).use(initRestService(config))
    } yield ExitCode.Success

    main.onErrorHandle(_=> ExitCode.Error)
  }

  private def initRestService(config: AppConfig)(xa: HikariTransactor[Task]): Task[Http.ServerBinding] = {
    val controllers = Seq(
      new UserController(new UserService(new UserDao, xa)),
      new ChallengeController(config.http, new ChallengeService(new ChallengeDao(), xa)),
      new ChallengeTypeController(config.http, new ChallengeTypeService(new ChallengeTypeDao(), xa))
    )

    val routes = new PublicApiEndpoint(controllers).route
    HttpServer(config.http, routes)
  }
}

