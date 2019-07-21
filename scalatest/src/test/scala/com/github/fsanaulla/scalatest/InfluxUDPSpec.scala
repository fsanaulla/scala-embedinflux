package com.github.fsanaulla.scalatest

import com.github.fsanaulla.chronicler.ahc.io.InfluxIO
import com.github.fsanaulla.chronicler.macros.annotations.{field, tag}
import com.github.fsanaulla.chronicler.macros.auto._
import com.github.fsanaulla.chronicler.udp.InfluxUdp
import com.github.fsanaulla.core.testing.configurations.InfluxUDPConf
import com.github.fsanaulla.scalatest.embedinflux.EmbeddedInfluxDB
import org.scalatest.concurrent.{Eventually, IntegrationPatience, ScalaFutures}
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers, TryValues}

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
    with TryValues
    with ScalaFutures
    with Eventually
    with IntegrationPatience {

  implicit override val patienceConfig: PatienceConfig =
    PatienceConfig(
      timeout = scaled(Span(60, Seconds)),
      interval = scaled(Span(1, Second))
    )

  final case class Test(@tag name: String, @field age: Int)

  lazy val influxHttp = InfluxIO("localhost", 8086)
  lazy val influxUdp = InfluxUdp("localhost", 8089)

  "InfluxDB" should "correctly work" in {

    val t = Test("f", 1)

    influxUdp
      .write[Test]("cpu", t)
      .success
      .value shouldEqual {}

    eventually {
      influxHttp
        .measurement[Test]("udp", "cpu")
        .read("SELECT * FROM cpu")
        .futureValue
        .right
        .get shouldEqual Array(t)
    }
  }
}
