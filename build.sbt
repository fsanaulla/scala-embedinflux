import sbt.Keys.{resolvers, scalaVersion}

lazy val commonSettings = Seq(
  version := "0.1.3",
  crossScalaVersions := Seq("2.11.11", "2.12.4"),
  organization := "com.github.fsanaulla",
  homepage := Some(url("https://github.com/fsanaulla/scala-embedinflux")),
  licenses += "Apache-2.0" -> url("https://opensource.org/licenses/Apache-2.0"),
  developers += Developer(id = "fsanaulla", name = "Faiaz Sanaulla", email = "fayaz.sanaulla@gmail.com", url = url("https://github.com/fsanaulla")),

  resolvers ++= Seq(Resolver.mavenLocal) // temporary
)

lazy val publishSettings = Seq(
  // Publish section
  useGpg := true,
  publishArtifact in Test := false,
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/fsanaulla/scala-embedinflux"),
      "https://github.com/fsanaulla/scala-embedinflux.git"
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
    libraryDependencies ++= Dependencies.scalaTestDep,
    parallelExecution := false
  )

lazy val specs2 = (project in file("specs2"))
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)
  .settings(
    name := "specs2-embedinflux",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Dependencies.specs2Dep,
    scalacOptions in Test ++= Seq("-Yrangepos")
  )