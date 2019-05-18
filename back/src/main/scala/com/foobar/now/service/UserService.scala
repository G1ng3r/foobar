package com.foobar.now.service

import com.foobar.now.dao.UserDao
import com.foobar.now.model.{SignIn, User}
import doobie.util.transactor.Transactor
import monix.eval.Task
import doobie.implicits._

class UserService(userDao: UserDao, xa: Transactor[Task]) {

  def signIn(si: SignIn): Task[User] = {
    userDao.get(si.loginOrEmail, si.password).transact(xa)
  }
}
