package com.github.fsanaulla.specs2

import com.paulgoldbaum.influxdbclient.{InfluxDB, Point, UdpClient}
import org.specs2._
import org.specs2.concurrent.ExecutionEnv

import scala.concurrent.duration._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 07.03.18
  */
class InfluxUdpSpec(implicit ee: ExecutionEnv)
  extends mutable.Specification
    with EmbeddedInfluxDB {

  override def udpPort = Some(8089)

  lazy val influxHttp: InfluxDB = InfluxDB.connect("localhost", httpPort)
  lazy val influxUdp: UdpClient = InfluxDB.udpConnect("localhost", udpPort.get)

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
