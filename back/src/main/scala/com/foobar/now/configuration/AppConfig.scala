package com.foobar.now.configuration

import monix.eval.Task
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.auto._

case class DatabaseConfig(driver: String,
                    connection: String,
                    username: String,
                    password: String)

case class HttpConfig(host: String, port: Int)

case class AppConfig(database: DatabaseConfig, http: HttpConfig)

object AppConfig {
  def apply(): Task[AppConfig] =
    Task.fromEither[ConfigReaderFailures, AppConfig](err => new Exception(err.toString))(pureconfig.loadConfig[AppConfig])
}