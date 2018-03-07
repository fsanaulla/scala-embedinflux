package com.github.fsanaulla.specs2

import io.apisense.embed.influx.InfluxServer
import io.apisense.embed.influx.configuration.InfluxConfigurationWriter
import org.specs2.specification.BeforeAfterAll

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
trait EmbeddedInfluxDB extends BeforeAfterAll {

  /** define HTTP port */
  def httpPort = 8086

  /** define back up port */
  def backUpPort = 8088

  /** defile UDP port, by default turned off */
  def udpPort: Option[Int] = None

  private def conf: InfluxConfigurationWriter = udpPort match {
    case Some(p) =>
      new InfluxConfigurationWriter(backUpPort, httpPort, p)
    case _ =>
      new InfluxConfigurationWriter(backUpPort, httpPort)
  }

  private val influx: InfluxServer =
    new InfluxServer
      .Builder()
      .setInfluxConfiguration(conf)
      .build()

  override def beforeAll: Unit = {
    influx.init()
    influx.start()
  }

  override def afterAll: Unit = influx.stop()

  /***
    * Clean up all resources
    */
  final def cleanUpResources(): Unit = influx.cleanup()
}
