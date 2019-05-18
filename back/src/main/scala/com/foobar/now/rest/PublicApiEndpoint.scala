package com.foobar.now.rest

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import cats.effect.Resource
import doobie.hikari.HikariTransactor
import monix.eval.Task

class PublicApiEndpoint(transactor: Resource[Task, HikariTransactor[Task]], routes: Seq[Route]) {
  private val prefix: String = "/api/v1"

  val route: Route = (get & path("hello")) (complete("123"))
}
