package com.foobar.now.rest.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.foobar.now.model.AccessToken
import doobie.hikari.HikariTransactor
import monix.eval.Task

class UserController(xa: HikariTransactor[Task]) extends Controller {

  override val route: Route = (get & path("handshake")) {
    val customTokenData = AccessToken(12, 13)
    complete("123")
  }
}