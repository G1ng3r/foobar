package com.foobar.now.rest.routes

import akka.http.scaladsl.server.Route
import doobie.hikari.HikariTransactor
import monix.eval.Task

class UserRoute(xa: HikariTransactor[Task]) extends Controller {
  override val route: Route = ???
}