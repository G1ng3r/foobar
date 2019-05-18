package com.foobar.now.service

import com.foobar.now.dao.ChallengeDao
import com.foobar.now.model.{Challenge, ChallengeStatus}
import doobie.util.transactor.Transactor
import monix.eval.Task
import doobie.implicits._

class ChallengeService(challengeDao: ChallengeDao, transactor: Transactor[Task]) {
  def getChallenge(id: Long): Task[Challenge] = {
    challengeDao.get(id).transact(transactor)
  }

  def getAssignedChallenges(userId: Long, limit: Int, offset: Int): Task[List[Challenge]] = {
    challengeDao.getAssigned(userId, ChallengeStatus.Assigned, limit, offset)
      .compile
      .toList
      .transact(transactor)
  }
}
