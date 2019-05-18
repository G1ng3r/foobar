package com.foobar.now.service

import com.foobar.now.dao.ChallengeDao
import com.foobar.now.model.ChallengeStatus.ChallengeStatus
import com.foobar.now.model.{Challenge, ChallengeStatus, ChallengeType}
import doobie.util.transactor.Transactor
import monix.eval.Task
import doobie.implicits._

class ChallengeService(challengeDao: ChallengeDao, transactor: Transactor[Task]) {
  def createChallenge(challengeType: ChallengeType,
                      creator: Long,
                      assignedTo: Long): Task[Unit] = {
    val challenge = Challenge(0, challengeType.id, creator, assignedTo, ChallengeStatus.Assigned)
    challengeDao.create(challenge).transact(transactor).map(_ => ())
  }

  def getChallenge(id: Long): Task[Challenge] = {
    challengeDao.get(id).transact(transactor)
  }

  def getAssignedChallenges(userId: Long, limit: Int, offset: Int): Task[List[Challenge]] = {
    challengeDao.getAssigned(userId, ChallengeStatus.Assigned, limit, offset)
      .compile
      .toList
      .transact(transactor)
  }

  def acceptChallenge(id: Long): Task[Unit] = {
    updateStatus(id, ChallengeStatus.Accepted)
  }

  def declineChallenge(id: Long): Task[Unit] = {
    updateStatus(id, ChallengeStatus.Declined)
  }

  def completeChallenge(id: Long): Task[Unit] = {
    updateStatus(id, ChallengeStatus.Completed)
  }

  private def updateStatus(id: Long, status: ChallengeStatus) = {
    challengeDao.setStatus(id, status)
      .transact(transactor)
      .map(_ => ())
  }
}
