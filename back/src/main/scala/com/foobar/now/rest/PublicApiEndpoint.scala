package com.foobar.now.rest

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import cats.effect.Resource
import doobie.hikari.HikariTransactor
import monix.eval.Task

class PublicApiEndpoint(transactor: Resource[Task, HikariTransactor[Task]]) {
  private val prefix: String = "/api/v1"

  val routes: Route = (get & path("hello")) (complete("123"))
}
