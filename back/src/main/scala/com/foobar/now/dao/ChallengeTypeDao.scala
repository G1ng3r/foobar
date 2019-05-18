package com.foobar.now.dao

import com.foobar.now.model.ChallengeType
import doobie.free.connection.ConnectionIO
import doobie.implicits._

class ChallengeTypeDao extends SqlPagination {
  def create(challengeType: ChallengeType): ConnectionIO[ChallengeType] = {
    sql"insert into challenge_type (title, description) values (${challengeType.title}, ${challengeType.description})"
      .update
      .withUniqueGeneratedKeys("id", "title", "description")
  }

  def list(limit: Int, offset: Int): fs2.Stream[ConnectionIO, ChallengeType] = {
    paginate(limit, offset)(
      sql"select id, title, description from challenge_type".query[ChallengeType]
    ).stream
  }

  def get(id: Int): ConnectionIO[ChallengeType] = {
    sql"select id, title, description from challenge_type where id = $id"
      .query[ChallengeType]
      .unique
  }
}
