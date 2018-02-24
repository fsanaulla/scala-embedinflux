package com.github.fsanaulla.spec2

import io.apisense.embed.influx.InfluxServer
import io.apisense.embed.influx.configuration.InfluxConfigurationWriter
import org.specs2.SpecificationLike
import org.specs2.specification.BeforeAfterAll

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
trait EmbeddedInfluxDB extends BeforeAfterAll { self: SpecificationLike =>

  val port = 8086
  val backUpPort = 8088

  private val server: InfluxServer =
    new InfluxServer
      .Builder()
      .setInfluxConfiguration(new InfluxConfigurationWriter(backUpPort, port))
      .build()

  override def beforeAll: Unit = {
    server.start()
  }

  override def afterAll: Unit = {
    server.stop()
  }
}
