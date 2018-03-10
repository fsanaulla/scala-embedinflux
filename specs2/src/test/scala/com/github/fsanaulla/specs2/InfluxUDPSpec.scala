package com.github.fsanaulla.specs2

import com.github.fsanaulla.core.testing.configurations.InfluxUDPConf
import com.paulgoldbaum.influxdbclient.{InfluxDB, Point, UdpClient}
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

  lazy val influxHttp: InfluxDB = InfluxDB.connect("localhost", httpPort)
  lazy val influxUdp: UdpClient = InfluxDB.udpConnect("localhost", udpPort)

  "InfluxDB" >> {
    "correctly work" in {
      val tp = Point("cpu").addTag("1", "1").addField("2", 2)

      influxUdp.write(tp) shouldEqual {}

      Thread.sleep(3000)

      influxHttp
        .selectDatabase("udp")
        .query("SELECT * FROM cpu")
        .map(_.series must not equalTo Nil)
        .await(retries = 2, timeout = 2.seconds)
    }
  }
}
