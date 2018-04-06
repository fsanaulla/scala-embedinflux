package com.github.fsanaulla.specs2

import com.github.fsanaulla.core.testing.InfluxConf
import io.apisense.embed.influx.InfluxServer
import org.specs2.mutable.SpecificationLike
import org.specs2.specification.BeforeAfterAll

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
trait EmbeddedInfluxDB extends BeforeAfterAll { self: SpecificationLike with InfluxConf =>

  private val influx: InfluxServer =
    new InfluxServer
      .Builder()
      .setInfluxConfiguration(configuration)
      .build()

  override def beforeAll: Unit = {
    influx.init()
    influx.start()

    Thread.sleep(1000)
  }

  override def afterAll: Unit = {
    influx.stop()
    influx.cleanup()

    Thread.sleep(1000)
  }
}
