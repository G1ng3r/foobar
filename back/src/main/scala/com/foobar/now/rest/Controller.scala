package com.foobar.now.rest

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class Controller {
  def route: Route =
    path("hello") {
      get {
        complete("OK")
      }
    }
}
