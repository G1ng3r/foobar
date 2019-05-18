package com.foobar.now.rest.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.foobar.now.service.ChallengeService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import monix.execution.Scheduler
import io.circe.generic.auto._

class ChallengeController(challengeService: ChallengeService)(implicit scheduler: Scheduler)
  extends Controller with FailFastCirceSupport {
  override val route: Route = pathPrefix("challenge") {
    (path(LongNumber) & get) { id =>
      complete(challengeService.getChallenge(id).runToFuture)
    } ~
    path() {

    }
  }

}
