/** Copyright © 2018 Honeywell International Inc. */

package com.github.fsanaulla.scalatest

import com.paulgoldbaum.influxdbclient._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
class InfluxSpec
  extends FlatSpec
    with Matchers
    with EmbeddedInfluxDB
    with ScalaFutures {

  implicit val pc: PatienceConfig = PatienceConfig(Span(20, Seconds), Span(1, Second))

  lazy val influx: InfluxDB = InfluxDB.connect("localhost")

  "InfluxDB" should "correctly work" in {
    influx.ping().futureValue.series shouldEqual Nil
  }
}
