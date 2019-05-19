package com.foobar.now.rest.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.foobar.now.configuration.HttpConfig
import com.foobar.now.service.ChallengeService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._

class ChallengeController(val config: HttpConfig,
                          challengeService: ChallengeService)
  extends Controller with UploadFilesSupport with FailFastCirceSupport with JwtSupport with MonixSupport {
  override val route: Route = withJwt(config.secretKey) { token =>
    pathPrefix("challenge") {
      pathPrefix(LongNumber) { challengeId =>
        (get & pathEndOrSingleSlash) {
          complete(challengeService.getChallenge(challengeId))
        } ~
        (delete & path("decline")) {
          complete(challengeService.declineChallenge(token.userId, challengeId))
        } ~
        (put & path("accept")) {
          complete(challengeService.acceptChallenge(token.userId, challengeId))
        } ~
          (put & path("complete")) {
            storeUploadedFile("proof", tempDestination) { case (metadata, file) =>
              if (metadata.contentType.mediaType.isImage) {
                complete(challengeService.completeChallenge(token.userId, challengeId, file))
              } else {
                complete(StatusCodes.BadRequest, "wrong proof photo uploaded")
              }
            }
          }
      } ~
      (path(IntNumber / "assign" / LongNumber) & post) { case (challengeTypeId, assignedTo) =>
        complete(challengeService.createChallenge(challengeTypeId, token.userId, assignedTo))
      } ~
      (path("random") & post) {
        complete(challengeService.getRandomChallenge(token.userId))
      } ~
      (path("random" / "list") & get) {
        complete(challengeService.listRandomChallengeTypes)
      } ~
      (path("assigned") & get & parameter('offset.as[Int].?) & parameter('limit.as[Int].?)) { case (offset, limit) =>
        complete(challengeService.getAssignedChallenges(token.userId, limit, offset))
      }
    }
  }

}
