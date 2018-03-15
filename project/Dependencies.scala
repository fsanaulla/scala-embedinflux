import sbt._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 28.02.18
  */
object Dependencies {

  final val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  final val specs2 = "org.specs2" %% "specs2-core" % "4.0.3"
  final val influxAsync = "com.github.fsanaulla" %% "chronicler-async-http" % "0.1.0" % Test
  final val influxUdp = "com.github.fsanaulla" %% "chronicler-udp" % "0.1.0" % Test

  final val embeddedInflux = "io.apisense.embed.influx" % "embed-influxDB" % "1.0.2"

  final val scalaTestDep = Seq(scalaTest, embeddedInflux, influxUdp, influxAsync)
  final val specs2Dep = Seq(specs2, embeddedInflux, influxUdp, influxAsync)
}
