package com.foobar.now

import akka.http.scaladsl.Http
import cats.effect._
import com.foobar.now.configuration.{AppConfig, DatabaseConfig}
import com.foobar.now.database.DatabaseTransactor
import com.foobar.now.rest.routes.UserRoute
import com.foobar.now.rest.{HttpServer, PublicApiEndpoint}
import com.typesafe.scalalogging.LazyLogging
import doobie.hikari.HikariTransactor
import monix.eval._

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
      new UserRoute(xa)
    )

    val routes = new PublicApiEndpoint(controllers).route
    HttpServer(config.http, routes)
  }
}

