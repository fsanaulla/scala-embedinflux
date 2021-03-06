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

package com.github.fsanaulla.scalatest.embedinflux

import com.github.fsanaulla.core.testing.InfluxConf
import io.apisense.embed.influx.InfluxServer
import io.apisense.embed.influx.configuration.VersionConfiguration
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
trait EmbeddedInfluxDB extends BeforeAndAfterAll { self: Suite with InfluxConf =>

  private val influx: InfluxServer =
    new InfluxServer
      .Builder()
      .setVersionConfig(VersionConfiguration.fromRuntime(version))
      .setInfluxConfiguration(configuration)
      .build()

  override def beforeAll(): Unit = {
    influx.init()
    influx.start()

    Thread.sleep(1000)
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    influx.stop()
    influx.cleanup()

    Thread.sleep(1000)
    super.afterAll()
  }
}
