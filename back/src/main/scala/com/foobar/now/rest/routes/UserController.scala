package com.foobar.now.rest.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.foobar.now.model.SignIn
import com.foobar.now.service.UserService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import MonixSupport._

class UserController(userService: UserService)
  extends Controller with FailFastCirceSupport with JwtSupport {

  override val route: Route = pathPrefix("user") {
    (post & path("signin")) {
      entity(as[SignIn]) (si => complete(userService.signIn(si)))
    }
  }
}