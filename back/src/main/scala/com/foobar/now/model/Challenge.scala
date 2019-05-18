package com.foobar.now.model

import com.foobar.now.model.ChallengeStatus.ChallengeStatus

object ChallengeStatus extends Enumeration {

  type ChallengeStatus = Value
  val Assigned = Value("assigned")
  val Accepted = Value("accepted")
  val Declined = Value("declined")
  val Completed = Value("completed")

}

case class ChallengeType(id: Int, title: String, description: String)

case class Challenge(id: Long,
                     typeId: Int,
                     creator: Long,
                     assigned: Long,
                     status: ChallengeStatus)
