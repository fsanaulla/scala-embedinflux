/*
 * Copyright 2017-2019 Faiaz Sanaulla
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fsanaulla.specs2

import com.github.fsanaulla.chronicler.ahc.io.{AhcIOClient, InfluxIO}
import com.github.fsanaulla.chronicler.macros.annotations.{field, tag}
import com.github.fsanaulla.chronicler.macros.auto._
import com.github.fsanaulla.chronicler.udp.{InfluxUDPClient, InfluxUdp}
import com.github.fsanaulla.core.testing.configurations.InfluxUDPConf
import com.github.fsanaulla.specs2.embedinflux.EmbeddedInfluxDB
import org.specs2._
import org.specs2.concurrent.ExecutionEnv

import scala.concurrent.duration._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 07.03.18
  */
class InfluxUDPSpec(implicit ee: ExecutionEnv)
  extends mutable.Specification
    with InfluxUDPConf
    with EmbeddedInfluxDB {

  args(skipAll = true)

  case class Test(@tag name: String, @field age: Int)

  lazy val influxHttp: AhcIOClient =
    InfluxIO("localhost", 8086)
  lazy val influxUdp: InfluxUDPClient =
    InfluxUdp("localhost", 8089)

  "InfluxDB" >> {
    "correctly work" in {
      val t = Test("f", 1)

      influxUdp
        .write[Test]("cpu", t)
        .get mustEqual {}

      influxHttp
        .measurement[Test]("udp", "cpu")
        .read("SELECT * FROM cpu")
        .map(_.right.get mustEqual Array(t))
        .await(retries = 2, timeout = 2.seconds)
    }
  }
}
