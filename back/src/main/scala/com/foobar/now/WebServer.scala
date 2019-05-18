package com.foobar.now

import cats.effect._
import com.foobar.now.configuration.AppConfig
import com.foobar.now.database.Postgres
import com.foobar.now.rest.{HttpServer, PublicApiEndpoint}
import com.typesafe.scalalogging.LazyLogging
import monix.eval._
import monix.execution.Scheduler.Implicits.global

object WebServer extends TaskApp with LazyLogging {

  def run(args: List[String]): Task[ExitCode] = {

    val main = for {
      config <- AppConfig()
      routes = new PublicApiEndpoint(Postgres(config.database)).routes
      _ <- HttpServer(config.http, routes)
    } yield ExitCode.Success

    main.onErrorHandle(_=> ExitCode.Error)
  }
}

