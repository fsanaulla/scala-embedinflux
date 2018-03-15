package com.github.fsanaulla.specs2

import com.github.fsanaulla.chronicler.async.{InfluxAsyncHttpClient, InfluxDB}
import com.github.fsanaulla.chronicler.udp.InfluxUdpClient
import com.github.fsanaulla.core.model.Point
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

  lazy val influxHttp: InfluxAsyncHttpClient =
    InfluxDB.connect("localhost", httpPort)
  lazy val influxUdp: InfluxUdpClient =
    com.github.fsanaulla.chronicler.udp.InfluxDB.connect("localhost", udpPort.get)

  "InfluxDB" >> {
    "correctly work" in {
      val tp = Point("cpu").addTag("1", "1").addField("2", 2)

      influxUdp
        .writePoint(tp)
        .map(u => u mustEqual {})
        .await(retries = 2, timeout = 2.seconds)

      Thread.sleep(3000)

      influxHttp
        .database("udp")
        .readJs("SELECT * FROM cpu")
        .map(_.queryResult must not equalTo Nil)
        .await(retries = 2, timeout = 2.seconds)
    }
  }
}
