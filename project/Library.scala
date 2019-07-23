import sbt._

/**
  * Created by
  * Author: fayaz.sanaulla@gmail.com
  * Date: 28.02.18
  */
object Library {

  object Versions {
    val chronicler = "0.5.4"
  }

  val scalaTest = "org.scalatest" %% "scalatest"   % "3.0.8" % Provided
  val specs2    = "org.specs2"    %% "specs2-core" % "4.6.0" % Provided

  val testing = List(
    "com.github.fsanaulla" %% "chronicler-url-io" % Versions.chronicler,
    "com.github.fsanaulla" %% "chronicler-udp"    % Versions.chronicler,
    "com.github.fsanaulla" %% "chronicler-macros" % Versions.chronicler
  )

  val embeddedInflux = "io.apisense.embed.influx" % "embed-influxDB" % "1.2.0"

  val scalaTestDep: List[ModuleID] = List(
    scalaTest,
    embeddedInflux
  ) ++ testing

  val specs2Dep: List[ModuleID] = List(
    specs2,
    embeddedInflux
  ) ++ testing
}
