package com.foobar.now.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import com.foobar.now.configuration.AppConfig
import monix.eval.Task

import scala.concurrent.{ExecutionContext, Future}

object HttpServer {
  implicit val actorSystem: ActorSystem = ActorSystem("foobar")
  implicit val materializer: Materializer = ActorMaterializer()

  implicit val executionContext: ExecutionContext = actorSystem.dispatcher

  def apply(config: AppConfig, routes: Route): Task[Http.ServerBinding] = {
    Task.deferFuture(
      Http().bindAndHandle(routes, config.http.host, config.http.port)
    )
  }
}
