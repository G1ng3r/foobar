package com.foobar.now.rest

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.foobar.now.rest.routes.Controller

class PublicApiEndpoint(controllers: Seq[Controller]) {
  val route: Route = pathPrefix("api" / "v1") {
    controllers.map(_.route).reduce(_ ~ _)
  } ~ path("ok") { complete("I am ok") }
}