package com.foobar.now.rest.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.foobar.now.configuration.HttpConfig
import com.foobar.now.service.ChallengeService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import monix.execution.Scheduler

class ChallengeController(config: HttpConfig,
                          challengeService: ChallengeService)
                         (implicit scheduler: Scheduler)
  extends Controller with FailFastCirceSupport with JwtSupport with MonixSupport {
  override val route: Route = withJwt(config.secretKey) { token =>
    pathPrefix("challenge") {
      pathPrefix(LongNumber) { id =>
        (get & pathEndOrSingleSlash) {
          complete(challengeService.getChallenge(id).runToFuture)
        } ~
        (delete & path("decline")) {
          complete(challengeService.declineChallenge(token.userId, id))
        } ~
        (put & path("accept")) {
          complete(challengeService.acceptChallenge(token.userId, id))
        }
      } ~
      (path(IntNumber / "assign" / LongNumber) & post) { case (challengeTypeId, assignedTo) =>
        complete(challengeService.createChallenge(challengeTypeId, token.userId, assignedTo))
      }
    }
  }

}
