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

import com.github.fsanaulla.chronicler.ahc.io.InfluxIO
import com.github.fsanaulla.core.testing.configurations.InfluxHTTPConf
import com.github.fsanaulla.specs2.embedinflux.EmbeddedInfluxDB
import org.specs2._
import org.specs2.concurrent.ExecutionEnv

import scala.concurrent.duration._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
class InfluxHTTPSpec(implicit ee: ExecutionEnv)
  extends mutable.Specification
    with InfluxHTTPConf
    with EmbeddedInfluxDB {

  lazy val influx =
    InfluxIO("localhost", 8086)

  "InfluxDB" >> {
    "ping databse" in {
      influx
        .ping
        .map(_.right.get.version mustEqual "1.7.6")
        .await(retries = 2, timeout = 2.seconds)
    }
  }
}
