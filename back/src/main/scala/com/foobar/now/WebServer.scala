package com.foobar.now

import cats.effect._
import cats.implicits._
import com.foobar.now.configuration.AppConfig
import com.foobar.now.rest.{Controller, HttpServer}
import com.typesafe.scalalogging.LazyLogging
import monix.eval._
import monix.execution.Scheduler.Implicits.global
import pureconfig.error.ConfigReaderFailures

object WebServer extends TaskApp with LazyLogging {

  def run(args: List[String]): Task[ExitCode] =
    for {
      config <- Task.fromEither[ConfigReaderFailures, AppConfig](err => new Exception(err.toString))(AppConfig())
      routes <- Task.eval(new Controller().route)
      _ <- HttpServer(config, routes)
    } yield ExitCode.Success

}

