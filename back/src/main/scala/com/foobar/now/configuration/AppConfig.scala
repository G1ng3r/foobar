package com.foobar.now.configuration

import com.typesafe.scalalogging.LazyLogging
import pureconfig.ConfigReader.Result
import pureconfig.generic.auto._

case class Database(driver: String,
                    connection: String,
                    username: String,
                    password: String)

case class Http(host: String, port: Int)

case class AppConfig(database: Database, http: Http)

object AppConfig {
  def apply(): Result[AppConfig] = pureconfig.loadConfig[AppConfig]
}
