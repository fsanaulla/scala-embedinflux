/** Copyright © 2018 Honeywell International Inc. */

package com.github.fsanaulla.scalatest

import com.paulgoldbaum.influxdbclient._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global

class InfluxSpec extends FlatSpec with Matchers with EmbeddedInfluxDB with ScalaFutures {

  implicit val pc: PatienceConfig = PatienceConfig(Span(20, Seconds), Span(1, Second))

  lazy val influxdb: InfluxDB = InfluxDB.connect("localhost", port)

  "InfluxDB" should "correctly work" in {
    influxdb.ping().futureValue.series shouldEqual Nil
  }
}
