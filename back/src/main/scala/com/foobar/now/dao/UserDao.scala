package com.foobar.now.dao

import com.foobar.now.model.User
import doobie.free.connection.ConnectionIO
import doobie.implicits._

class UserDao extends SqlPagination {

  def signIn(loginOrEmail: String, password: String): ConnectionIO[User] = {
    sql"""select id, login, name, email, location, avatar, karma from public.user
         |where (login = $loginOrEmail and password = $password)
         |or (login = $loginOrEmail and password = $password)""".stripMargin
      .query[User]
      .unique
  }

  def get(id: Long): ConnectionIO[User] = {
    sql"select id, login, name, email, location, avatar, karma from public.user where id = $id"
      .query[User]
      .unique
  }

  def list(userId: Long, limit: Int): fs2.Stream[ConnectionIO, User] = {
    sql"""select id, login, name, email, location, avatar, karma from public.user
         |where id <> $userId order by random() limit $limit
       """.stripMargin
      .query[User]
      .stream
  }

  def near(userId: Long, limit: Int): fs2.Stream[ConnectionIO, User] = {
    sql"""select id, login, name, email, location, avatar, karma from public.user
         |where id <> $userId and location = (select location from public.user where id = $userId)
         |order by random() limit $limit
       """.stripMargin
      .query[User]
      .stream
  }

  def friends(userId: Long, limit: Int): fs2.Stream[ConnectionIO, User] = {
    sql"""select u.id, u.login, u.name, u.email, u.location, u.avatar, u.karma from public.user u, friends f
         |where f.user_id = $userId and u.id = f.friend_id
         |order by random() limit $limit
       """.stripMargin
      .query[User]
      .stream
  }
}
