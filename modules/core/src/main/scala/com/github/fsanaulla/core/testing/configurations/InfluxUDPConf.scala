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

package com.github.fsanaulla.core.testing.configurations

import io.apisense.embed.influx.configuration.InfluxConfigurationWriter

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 10.03.18
  */
trait InfluxUDPConf extends InfluxHTTPConf {

  /**
    * UDP protocol databse storage name
    */
  def database: String = "udp"

  /**
    * UDP interface port value
    */
  def udpPort: Int = 8089

  override def configuration: InfluxConfigurationWriter = {
    new InfluxConfigurationWriter.Builder()
      .setHttp(httpPort, auth)
      .setUdp(udpPort, database)
      .build()
  }

}
