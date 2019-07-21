package com.github.fsanaulla.specs2

import com.github.fsanaulla.chronicler.ahc.io.InfluxIO
import com.github.fsanaulla.core.testing.configurations.InfluxHTTPConf
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

  lazy val influx =
    InfluxIO("localhost", 8086)

  "InfluxDB" >> {
    "ping databse" in {
      influx
        .ping
        .map(_.right.get.version mustEqual "1.7.6")
        .await(retries = 2, timeout = 2.seconds)
    }
  }
}
