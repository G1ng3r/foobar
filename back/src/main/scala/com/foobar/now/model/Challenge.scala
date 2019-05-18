package com.foobar.now.model

import com.foobar.now.model.ChallengeStatus.ChallengeStatus
import doobie.enum.JdbcType.VarChar
import doobie.util.Meta.Basic
import doobie.util.Meta
import doobie.enum.JdbcType._
import io.circe.{Decoder, Encoder}

object ChallengeStatus extends Enumeration {

  type ChallengeStatus = Value
  val Assigned = Value("assigned")
  val Accepted = Value("accepted")
  val Declined = Value("declined")
  val Completed = Value("completed")

  implicit val challengeStatusDoobieRead: Meta[ChallengeStatus] =
    Basic.one[ChallengeStatus](
      VarChar,
      List(VarChar, Char, LongVarChar, NChar, NVarChar, LongnVarChar),
      (st, i) => ChallengeStatus.withName(st.getString(i)),
      (st, i, status) => st.setString(i, status.toString),
      (st, i, status) => st.updateString(i, status.toString)
    )

  implicit val encoder: Encoder[ChallengeStatus] = Encoder.enumEncoder(ChallengeStatus)

  implicit val decoder: Decoder[ChallengeStatus] = Decoder.enumDecoder(ChallengeStatus)

}

case class ChallengeType(id: Int, title: String, description: String)

case class Challenge(id: Long,
                     typeId: Int,
                     creator: Long,
                     assigned: Long,
                     status: ChallengeStatus)
