package com.github.fsanaulla.specs2

import com.paulgoldbaum.influxdbclient.InfluxDB
import org.specs2._
import org.specs2.concurrent.ExecutionEnv

import scala.concurrent.duration._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
class InfluxHttpSpec(implicit ee: ExecutionEnv)
  extends mutable.Specification
    with EmbeddedInfluxDB {

  lazy val influx: InfluxDB = InfluxDB.connect("localhost")

  "InfluxDB" >> {
    "ping databse" in {
      influx.ping().map(_.series must be equalTo Nil).await(retries = 2, timeout = 2.seconds)
    }
  }
}
