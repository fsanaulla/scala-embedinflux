package com.github.fsanaulla.core.testing

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 10.03.18
  */
trait InfluxConf {

  /**
    * Configuration for running InfluxDB
    */
  def configuration: InfluxConfigurationWriter
}
