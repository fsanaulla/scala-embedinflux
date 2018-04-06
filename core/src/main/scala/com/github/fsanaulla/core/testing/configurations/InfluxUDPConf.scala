package com.github.fsanaulla.core.testing.configurations

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
