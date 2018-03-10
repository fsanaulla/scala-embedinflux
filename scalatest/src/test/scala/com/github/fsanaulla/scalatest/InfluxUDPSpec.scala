package com.github.fsanaulla.scalatest

import com.github.fsanaulla.core.testing.configurations.InfluxUDPConf
import com.paulgoldbaum.influxdbclient.{InfluxDB, Point, UdpClient}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 27.02.18
  */
class InfluxUDPSpec
  extends FlatSpec
    with Matchers
    with EmbeddedInfluxDB
    with InfluxUDPConf
    with ScalaFutures {

  implicit val pc: PatienceConfig = PatienceConfig(Span(20, Seconds), Span(1, Second))

  lazy val influxHttp: InfluxDB = InfluxDB.connect("localhost", 8086)
  lazy val influxUdp: UdpClient = InfluxDB.udpConnect("localhost", 8089)

  "InfluxDB" should "correctly work" in {
    val tp = Point("cpu").addTag("1", "1").addField("2", 2)

    influxUdp.write(tp) shouldEqual {}

    Thread.sleep(3000)

    influxHttp
      .selectDatabase("udp")
      .query("SELECT * FROM cpu")
      .futureValue
      .series should not equal Nil
  }
}
