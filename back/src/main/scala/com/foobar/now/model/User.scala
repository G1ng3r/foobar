package com.foobar.now.model

case class User(
               id: Long,
               login: String,
               email: String,
               avatar: String,
               karma: Int
               )