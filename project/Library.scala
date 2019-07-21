import sbt._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 28.02.18
  */
object Library {

  object Versions {
    final val chronicler = "0.5.4"
  }

  final val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % Provided
  final val specs2 = "org.specs2" %% "specs2-core" % "4.0.3" % Provided
  final val chroniclerAsync = "com.github.fsanaulla" %% "chronicler-ahc-io" % Versions.chronicler % Test
  final val chroniclerUdp = "com.github.fsanaulla" %% "chronicler-udp" % Versions.chronicler % Test
  final val chroniclerMacros = "com.github.fsanaulla" %% "chronicler-macros" % Versions.chronicler % Test

  final val embeddedInflux = "io.apisense.embed.influx" % "embed-influxDB" % "1.2.0"

  final val scalaTestDep = Seq(
    scalaTest,
    embeddedInflux,
    chroniclerUdp,
    chroniclerAsync,
    chroniclerMacros
  )
  final val specs2Dep = Seq(
    specs2,
    embeddedInflux,
    chroniclerUdp,
    chroniclerAsync,
    chroniclerMacros
  )
}
