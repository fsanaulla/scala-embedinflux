package com.github.fsanaulla.core.testing.configurations

import com.github.fsanaulla.core.testing.InfluxConf
import io.apisense.embed.influx.configuration.InfluxConfigurationWriter

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 10.03.18
  */
trait InfluxUDPConf extends InfluxConf {

  /**
    * UDP protocol databse storage name
    */
  def database: String = "udp"

  /**
    * HTTP interface port value
    */
  def httpPort: Int = 8086

  /**
    * UDP interface port value
    */
  def udpPort: Int = 8089

  override def configuration: InfluxConfigurationWriter = {
    new InfluxConfigurationWriter.Builder()
      .setHttp(httpPort)
      .setUdp(udpPort, database)
      .build()
  }

}
