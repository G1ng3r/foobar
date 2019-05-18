package com.foobar.now.dao

import com.foobar.now.model.User
import doobie.free.connection.ConnectionIO
import doobie.implicits._

class UserDao extends SqlPagination {

  def get(loginOrEmail: String, password: String): ConnectionIO[User] = {
    sql"""select * from user
         |where (login = $loginOrEmail and password = $password)
         |or (login = $loginOrEmail and password = $password)""".stripMargin
      .query[User]
      .unique
  }
}
