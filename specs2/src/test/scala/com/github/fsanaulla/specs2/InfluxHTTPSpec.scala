package com.github.fsanaulla.specs2

import com.github.fsanaulla.chronicler.async.InfluxDB
import org.specs2._
import org.specs2.concurrent.ExecutionEnv

import scala.concurrent.duration._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
class InfluxHTTPSpec(implicit ee: ExecutionEnv)
  extends mutable.Specification
    with InfluxHTTPConf
    with EmbeddedInfluxDB {

  lazy val influx = InfluxDB.connect("localhost")

  "InfluxDB" >> {
    "ping databse" in {
      influx.ping().map(_.isSuccess mustEqual true).await(retries = 2, timeout = 2.seconds)
    }
  }
}
