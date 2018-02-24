import sbt.Keys.scalaVersion

lazy val commonSettings = Seq(
  version := "0.1.1",
  crossScalaVersions := Seq("2.11.11", "2.12.4"),
  organization := "com.github.fsanaulla",
  homepage := Some(url("https://github.com/fsanaulla/chronicler")),
  licenses += "MIT" -> url("https://opensource.org/licenses/MIT"),
  developers += Developer(id = "fsanaulla", name = "Faiaz Sanaulla", email = "fayaz.sanaulla@gmail.com", url = url("https://github.com/fsanaulla"))
)

lazy val publishSettings = Seq(
  // Publish section
  useGpg := true,
  publishArtifact in Test := false,
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/fsanaulla/chronicler"),
      "https://github.com/fsanaulla/chronicler.git"
    )
  ),
  pomIncludeRepository := (_ => false),
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  )
)

lazy val root = (project in file("."))
  .aggregate(scalaTest, specs2)

lazy val scalaTest = (project in file("scalatest"))
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)
  .settings(
    name := "scalatest-embedinflux",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5",
      "io.apisense.embed.influx" % "embed-influxDB" % "1.0.0",
      "com.paulgoldbaum" %% "scala-influxdb-client" % "0.5.2" // todo: move to chronicler, when it will be published)
  ))

lazy val specs2 = (project in file("specs2"))
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)
  .settings(
    name := "specs2-embedinflux",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "4.0.3",
      "io.apisense.embed.influx" % "embed-influxDB" % "1.0.0",
      "com.paulgoldbaum" %% "scala-influxdb-client" % "0.5.2" // todo: move to chronicler, when it will be published)
    ),
    scalacOptions in Test ++= Seq("-Yrangepos")
  )