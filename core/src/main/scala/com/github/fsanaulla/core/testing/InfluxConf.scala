package com.github.fsanaulla.core.testing

import io.apisense.embed.influx.configuration.{InfluxConfigurationWriter, InfluxVersion}

/***
  * Provide necessary behavior for configure Embedded InfluxDB
  */
trait InfluxConf {

  /**
    * InfluxDB version, override to change it
    *
    * @return
    */
  def version: InfluxVersion = InfluxVersion.PRODUCTION

  /**
    * Configuration for running InfluxDB
    */
  def configuration: InfluxConfigurationWriter
}
