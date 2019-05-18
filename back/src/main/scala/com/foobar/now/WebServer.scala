package com.foobar.now

import cats.effect._
import com.foobar.now.configuration.{AppConfig, DatabaseConfig}
import com.foobar.now.database.DatabaseTransactor
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

  private def initRestService(config: AppConfig)(xa: HikariTransactor[Task]) = {
    val routes = new PublicApiEndpoint(DatabaseTransactor(config.database)).routes
    HttpServer(config.http, routes)
  }
}

