package com.foobar.now.service

import com.foobar.now.dao.ChallengeDao
import com.foobar.now.model.ChallengeStatus.ChallengeStatus
import com.foobar.now.model.{Challenge, ChallengeStatus}
import doobie.util.transactor.Transactor
import monix.eval.Task
import doobie.implicits._

class ChallengeService(challengeDao: ChallengeDao, xa: Transactor[Task]) {
  def createChallenge(challengeTypeId: Int,
                      creator: Long,
                      assignedTo: Long): Task[Unit] = {
    val challenge = Challenge(0, challengeTypeId, creator, assignedTo, ChallengeStatus.Assigned)
    challengeDao.create(challenge).transact(xa).map(_ => ())
  }

  def getChallenge(id: Long): Task[Challenge] = {
    challengeDao.get(id).transact(xa)
  }

  def getAssignedChallenges(userId: Long, limit: Int, offset: Int): Task[List[Challenge]] = {
    challengeDao.getAssigned(userId, ChallengeStatus.Assigned, limit, offset)
      .compile
      .toList
      .transact(xa)
  }

  def acceptChallenge(userId: Long, id: Long): Task[Unit] = {
    updateStatus(id, userId, ChallengeStatus.Accepted)
  }

  def declineChallenge(userId: Long, id: Long): Task[Unit] = {
    updateStatus(id, userId, ChallengeStatus.Declined)
  }

  def completeChallenge(userId: Long, id: Long): Task[Unit] = {
    updateStatus(id, userId, ChallengeStatus.Completed)
  }

  private def updateStatus(userId: Long, id: Long, status: ChallengeStatus) = {
    challengeDao.setStatus(id, userId, status)
      .transact(xa)
      .map(_ => ())
  }
}
