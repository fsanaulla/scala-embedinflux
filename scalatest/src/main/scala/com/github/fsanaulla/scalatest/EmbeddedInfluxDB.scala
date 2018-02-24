/** Copyright © 2018 Honeywell International Inc. */

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

  val port = 8086
  val backUpPort = 8088

  private val server: InfluxServer =
    new InfluxServer
      .Builder()
      .setInfluxConfiguration(new InfluxConfigurationWriter(backUpPort, port))
      .build()

  override def beforeAll(): Unit = {
    server.start()
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    server.stop()
    super.afterAll()
  }
}
