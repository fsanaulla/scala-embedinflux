package com.github.fsanaulla.scalatest

import com.github.fsanaulla.chronicler.async.{InfluxAsyncHttpClient, InfluxDB}
import com.github.fsanaulla.chronicler.udp.InfluxUdpClient
import com.github.fsanaulla.core.model.Point
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 27.02.18
  */
class InfluxUdpSpec
  extends FlatSpec
    with Matchers
    with EmbeddedInfluxDB
    with ScalaFutures {

  implicit val pc: PatienceConfig = PatienceConfig(Span(20, Seconds), Span(1, Second))

  override def udpPort = Some(8089)

  lazy val influxHttp: InfluxAsyncHttpClient =
    InfluxDB.connect("localhost", httpPort)
  lazy val influxUdp: InfluxUdpClient =
    com.github.fsanaulla.chronicler.udp.InfluxDB.connect("localhost", udpPort.get)

  "InfluxDB" should "correctly work" in {
    val tp = Point("cpu").addTag("1", "1").addField("2", 2)

    influxUdp.writePoint(tp).futureValue shouldEqual {}

    Thread.sleep(3000)

    influxHttp
      .database("udp")
      .readJs("SELECT * FROM cpu")
      .futureValue
      .queryResult should not equal Nil
  }
}
