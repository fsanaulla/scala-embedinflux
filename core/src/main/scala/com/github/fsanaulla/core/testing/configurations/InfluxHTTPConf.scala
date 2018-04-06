package com.github.fsanaulla.core.testing.configurations

import com.github.fsanaulla.core.testing.InfluxConf
import io.apisense.embed.influx.configuration.InfluxConfigurationWriter

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 10.03.18
  */
trait InfluxHTTPConf extends InfluxConf {

  /**
    * Parameter for enabling/disabling auth for InfluxDB, by default disabled
    */
  def auth: Boolean = false

  /**
    * HTTP interface port value
    */
  def httpPort: Int = 8086

  override def configuration: InfluxConfigurationWriter = {
    new InfluxConfigurationWriter.Builder()
      .setHttp(httpPort, auth)
      .build()
  }
}
