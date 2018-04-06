package com.github.fsanaulla.scalatest

import com.github.fsanaulla.chronicler.async.{InfluxAsyncHttpClient, InfluxDB}
import com.github.fsanaulla.chronicler.udp.{InfluxUDP, InfluxUDPClient}
import com.github.fsanaulla.core.model.{InfluxFormatter, Point}
import com.github.fsanaulla.macros.Macros
import com.github.fsanaulla.macros.annotations.{field, tag}
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

  implicit val pc: PatienceConfig =
    PatienceConfig(Span(20, Seconds), Span(1, Second))

  case class Test(@tag name: String, @field age: Int)

  implicit val fmt: InfluxFormatter[Test] = Macros.format[Test]

  lazy val influxHttp: InfluxAsyncHttpClient =
    InfluxDB.connect()
  lazy val influxUdp: InfluxUDPClient =
    InfluxUDP.connect()

  "InfluxDB" should "correctly work" in {

    val t = Test("f", 1)

    influxUdp
      .write[Test]("cpu", t)
      .futureValue shouldEqual {}

    Thread.sleep(3000)

    influxHttp
      .database("udp")
      .read[Test]("SELECT * FROM cpu")
      .futureValue
      .queryResult shouldEqual Seq(t)
  }
}
