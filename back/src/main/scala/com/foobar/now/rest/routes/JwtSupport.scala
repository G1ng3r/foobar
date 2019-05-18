package com.foobar.now.rest.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives._
import io.circe.generic.auto._
import com.foobar.now.model.AccessToken
import pdi.jwt.{JwtAlgorithm, JwtCirce}

trait JwtSupport {
  def withJwt(secretKey: String): Directive1[AccessToken] = {
    optionalHeaderValueByName("Authorization").flatMap {
      case Some(t) =>
        val maybeToken = for {
          jwt <- readJwt(t, secretKey)
          token <- jwt.as[AccessToken]
        } yield token

        maybeToken.fold(_ => error400, provideIfNotExpired)
      case None => error400
    }
  }

  private def readJwt(jwt: String, secretKey: String) =
    JwtCirce.decodeJson(jwt, secretKey, Seq(JwtAlgorithm.HS256)).toEither

  private def provideIfNotExpired(token: AccessToken): Directive1[AccessToken] =
    if (token.expiredAt < System.currentTimeMillis) error401
    else provide(token)

  private def error400 = complete(StatusCodes.Unauthorized -> "No valid token provided")
  private def error401 = complete(StatusCodes.Unauthorized -> "Token expired")
}
