package com.foobar.now.service

import com.foobar.now.configuration.KarmaConfig
import com.foobar.now.dao.{ChallengeDao, ChallengeTypeDao, UserDao}
import com.foobar.now.model.ChallengeStatus.ChallengeStatus
import com.foobar.now.model.{Challenge, ChallengeStatus, ChallengeType}
import doobie.free.connection
import doobie.util.transactor.Transactor
import monix.eval.Task
import doobie.implicits._

class ChallengeService(config: KarmaConfig,
                       challengeTypeDao: ChallengeTypeDao,
                       challengeDao: ChallengeDao,
                       userDao: UserDao,
                       xa: Transactor[Task]) {
  def createChallenge(challengeTypeId: Int,
                      creator: Long,
                      assignedTo: Long): Task[Unit] = {
    if (creator == assignedTo) {
      Task.raiseError(new Exception("Users are unable to assign challenge to themselves"))
    } else {
      val challenge = Challenge(0, challengeTypeId, creator, assignedTo, ChallengeStatus.Assigned)
      challengeDao.create(challenge).transact(xa).map(_ => ())
    }
  }

  def getRandomChallenge(userId: Long): Task[Challenge] = {
    (for {
      challengeType <- challengeTypeDao.getRandom
      newChallenge = Challenge(0, challengeType.id, 0, userId, ChallengeStatus.Assigned)
      challenge <- challengeDao.create(newChallenge)
    } yield challenge).transact(xa)
  }

  def listRandomChallengeTypes: Task[Seq[ChallengeType]] = {
    challengeTypeDao.listRandom.compile.toList.transact(xa)
  }

  def getChallenge(id: Long): Task[Challenge] = {
    challengeDao.get(id).transact(xa)
  }

  def getAssignedChallenges(userId: Long, limit: Option[Int], offset: Option[Int]): Task[List[Challenge]] = {
    challengeDao.getAssigned(userId, ChallengeStatus.Assigned, limit.getOrElse(10), offset.getOrElse(0))
      .compile
      .toList
      .transact(xa)
  }

  def acceptChallenge(userId: Long, id: Long): Task[Unit] = {
    updateStatus(id, userId, ChallengeStatus.Accepted)
      .transact(xa)
      .map(_ => ())
  }

  def declineChallenge(userId: Long, id: Long): Task[Unit] = {
    (for {
      challenge <- updateStatus(id, userId, ChallengeStatus.Declined)
      _ <- if (challenge.status == ChallengeStatus.Accepted) penalty(challenge.assigned)
           else connection.unit
    } yield ()).transact(xa)
  }

  def completeChallenge(userId: Long, id: Long): Task[Unit] = {
    (for {
      challenge <- updateStatus(id, userId, ChallengeStatus.Completed)
      challengeType <- challengeTypeDao.get(challenge.typeId)
      _ <- userDao.updateKarma(challenge.assigned, challengeType.difficulty.id)
      _ <- userDao.becomeFriend(userId, challenge.assigned)
    } yield ()).transact(xa)
  }

  private def updateStatus(userId: Long, id: Long, status: ChallengeStatus) = {
    challengeDao.setStatus(id, userId, status)
  }

  private def penalty(assignedTo: Long) = {
    for {
      user <- userDao.get(assignedTo)
      newKarmaValue = user.karma - config.declineDecrease
      _ <- userDao.updateKarma(user.id, if (newKarmaValue < 0) 0 else newKarmaValue)
    } yield ()
  }
}
