package com.foobar.now.dao

import cats.data.NonEmptyList
import com.foobar.now.model.Challenge
import com.foobar.now.model.ChallengeStatus.ChallengeStatus
import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie._

class ChallengeDao extends SqlPagination {
  def create(challenge: Challenge): ConnectionIO[Challenge] = {
    sql"""insert into challenge (type_id, creator, assigned, status)
         |values (${challenge.typeId}, ${challenge.creator}, ${challenge.assigned}, ${challenge.status})
         |""".stripMargin
      .update
      .withUniqueGeneratedKeys("id", "type_id", "creator", "assigned", "status")
  }

  def get(id: Long): ConnectionIO[Challenge] = {
    sql"select id, type_id, creator, assigned, status from challenge where id = $id"
      .query[Challenge]
      .unique
  }

  def getAssigned(userId: Long, status: ChallengeStatus, limit: Int, offset: Int): fs2.Stream[ConnectionIO, Challenge] = {
    paginate(limit, offset)(
      sql"select id, type_id, creator, assigned, status from challenge where assigned = $userId and status = $status".query[Challenge]
    ).stream
  }

  def getById(ids: NonEmptyList[Long], limit: Int, offset: Int): fs2.Stream[ConnectionIO, Challenge] = {
    paginate(limit, offset)(
      (sql"select id, type_id, creator, assigned, status from challenge where " ++ Fragments.in(fr"id", ids)).query[Challenge]
    ).stream
  }

  def setStatus(userId: Long, id: Long, status: ChallengeStatus): ConnectionIO[Challenge] = {
    sql"update challenge set status = $status where id = $id and assigned = $userId"
      .update
      .withUniqueGeneratedKeys("id", "type_id", "creator", "assigned", "status")
  }
}

object ChallengeDao {
  def apply(): ChallengeDao = new ChallengeDao()
}