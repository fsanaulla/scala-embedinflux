import sbt._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 28.02.18
  */
object Dependencies {

  final val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  final val specs2 = "org.specs2" %% "specs2-core" % "4.0.3"

  final val embeddedInflux = "io.apisense.embed.influx" % "embed-influxDB" % "1.1.0-SNAPSHOT"

  final val influxClient = "com.paulgoldbaum" %% "scala-influxdb-client" % "0.5.2"

  final val scalaTestDep = Seq(scalaTest, embeddedInflux, influxClient)
  final val specs2Dep = Seq(specs2, embeddedInflux, influxClient)
}
