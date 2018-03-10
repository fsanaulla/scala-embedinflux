package com.github.fsanaulla.scalatest

import com.github.fsanaulla.core.testing.InfluxConf
import io.apisense.embed.influx.InfluxServer
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
trait EmbeddedInfluxDB extends BeforeAndAfterAll { self: Suite with InfluxConf =>

  private val influx: InfluxServer =
    new InfluxServer
      .Builder()
      .setInfluxConfiguration(configuration)
      .build()

  override def beforeAll(): Unit = {
    influx.init()
    influx.start()

    Thread.sleep(1000)
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    influx.stop()
    influx.cleanup()

    Thread.sleep(1000)
    super.afterAll()
  }
}
