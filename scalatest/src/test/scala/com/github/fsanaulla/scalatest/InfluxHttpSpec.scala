package com.github.fsanaulla.scalatest

import com.github.fsanaulla.chronicler.ahc.io.InfluxIO
import com.github.fsanaulla.core.testing.configurations.InfluxHTTPConf
import com.github.fsanaulla.scalatest.embedinflux.EmbeddedInfluxDB
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
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
    with ScalaFutures
    with IntegrationPatience {

  lazy val influx =
    InfluxIO("localhost", 8086)

  "InfluxDB" should "correctly work" in {
    influx.ping.futureValue.right.get.version shouldEqual "1.7.6"
  }
}
