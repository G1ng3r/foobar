package com.foobar.now

import akka.http.scaladsl.Http
import cats.effect._
import com.foobar.now.configuration.AppConfig
import com.foobar.now.database.DatabaseTransactor
import com.foobar.now.rest.routes.UserController
import com.foobar.now.rest.{HttpServer, PublicApiEndpoint}
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
      new UserController(xa)
    )

    val routes = new PublicApiEndpoint(controllers).route
    HttpServer(config.http, routes)
  }
}

