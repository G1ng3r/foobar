package com.foobar.now

import cats.effect._
import cats.implicits._
import monix.eval._

object WebServer extends TaskApp {

  def run(args: List[String]): Task[ExitCode] =
    args.headOption match {
      case Some(name) =>
        Task(println(s"Hello, $name!")).as(ExitCode.Success)
      case None =>
        Task(System.err.println("Usage: Hello name")).as(ExitCode(2))
    }
}

