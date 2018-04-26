package com.github.fsanaulla.specs2

import com.github.fsanaulla.chronicler.async.{InfluxAsyncHttpClient, InfluxDB}
import com.github.fsanaulla.chronicler.udp.{InfluxUDP, InfluxUDPClient}
import com.github.fsanaulla.core.model.{InfluxFormatter, Point}
import com.github.fsanaulla.core.testing.configurations.InfluxUDPConf
import com.github.fsanaulla.macros.Macros
import com.github.fsanaulla.macros.annotations.{field, tag}
import org.specs2._
import org.specs2.concurrent.ExecutionEnv

import scala.concurrent.duration._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 07.03.18
  */
class InfluxUDPSpec(implicit ee: ExecutionEnv)
  extends mutable.Specification
    with InfluxUDPConf
    with EmbeddedInfluxDB {

  case class Test(@tag name: String, @field age: Int)

  implicit val fmt: InfluxFormatter[Test] = Macros.format[Test]

  lazy val influxHttp: InfluxAsyncHttpClient =
    InfluxDB.connect()
  lazy val influxUdp: InfluxUDPClient =
    InfluxUDP.connect()

  "InfluxDB" >> {
    "correctly work" in {
      val t = Test("f", 1)

      influxUdp
        .write[Test]("cpu", t)
        .map(u => u mustEqual {})
        .await(retries = 2, timeout = 2.seconds)

      Thread.sleep(3000)

      influxHttp
        .database("udp")
        .read[Test]("SELECT * FROM cpu")
        .map(_.queryResult mustEqual Array(t))
        .await(retries = 2, timeout = 2.seconds)
    }
  }
}
