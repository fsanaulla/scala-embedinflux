package com.github.fsanaulla.scalatest

import com.github.fsanaulla.chronicler.async.{InfluxAsyncHttpClient, InfluxDB}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
class InfluxHttpSpec
  extends FlatSpec
    with Matchers
    with EmbeddedInfluxDB
    with InfluxHTTPConf
    with ScalaFutures {

  implicit val pc: PatienceConfig = PatienceConfig(Span(20, Seconds), Span(1, Second))

  lazy val influx: InfluxAsyncHttpClient =
    InfluxDB.connect()

  "InfluxDB" should "correctly work" in {
    influx.ping().futureValue.isSuccess shouldEqual true
  }
}
