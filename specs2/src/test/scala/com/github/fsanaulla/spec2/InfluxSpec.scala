package com.github.fsanaulla.spec2

import com.paulgoldbaum.influxdbclient.InfluxDB
import org.specs2.Specification
import org.specs2.concurrent.ExecutionEnv
import org.specs2.execute.Result
import org.specs2.specification.core.SpecStructure

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 23.02.18
  */
class InfluxSpec(implicit ee: ExecutionEnv)
  extends Specification
    with EmbeddedInfluxDB {

  def is: SpecStructure = sequential ^ s"""
    The `InfluxDB` should
      correctly work          $e1"""

  val influx: InfluxDB = InfluxDB.connect("localhost", port)

  def e1: Result = (for {
    res <- influx.ping()
  } yield res.series must be equalTo Nil).await
}
