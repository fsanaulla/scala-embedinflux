package com.github.fsanaulla.scalatest

import io.apisense.embed.influx.InfluxServer
import io.apisense.embed.influx.configuration.InfluxConfigurationWriter
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
trait EmbeddedInfluxDB extends BeforeAndAfterAll { self: Suite =>

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

  override def beforeAll(): Unit = {
    influx.init()
    influx.start()

    Thread.sleep(1000)
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    influx.stop()

    Thread.sleep(1000)
    super.afterAll()
  }

  final def cleanUpResources(): Unit = influx.cleanup()
}
