package com.foobar.now.rest

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.foobar.now.rest.routes.Controller

class PublicApiEndpoint(controllers: Seq[Controller]) {
  private val prefix: String = "/api/v1"

  val route: Route = path(prefix) {
    controllers.map(_.route).reduce(_ ~ _)
  }
}