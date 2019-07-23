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
