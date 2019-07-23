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

package com.github.fsanaulla.scalatest

import com.github.fsanaulla.chronicler.ahc.io.InfluxIO
import com.github.fsanaulla.chronicler.macros.annotations.{field, tag}
import com.github.fsanaulla.chronicler.macros.auto._
import com.github.fsanaulla.chronicler.udp.InfluxUdp
import com.github.fsanaulla.core.testing.configurations.InfluxUDPConf
import com.github.fsanaulla.scalatest.embedinflux.EmbeddedInfluxDB
import org.scalatest.concurrent.{Eventually, IntegrationPatience, ScalaFutures}
import org.scalatest.time.{Second, Seconds, Span}
import org.scalatest.{FlatSpec, Ignore, Matchers, TryValues}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 27.02.18
  */
@Ignore
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
