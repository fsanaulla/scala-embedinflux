package com.github.fsanaulla.core.testing

import io.apisense.embed.influx.configuration.InfluxConfigurationWriter

/***
  * Provide necessary behavior for configure Embedded InfluxDB
  */
trait InfluxConf {

  /**
    * Configuration for running InfluxDB
    */
  def configuration: InfluxConfigurationWriter
}
