lazy val `scala-embedinflux` = (project in file("."))
  .settings(Settings.common: _*)
  .settings(publishArtifact := false)
//  .aggregate(
//    core,
//    scalaTest,
//    specs2
//  )

lazy val core = project.in(file("modules/core"))
  .settings(
    name := "core-testing",
    libraryDependencies += Library.embeddedInflux)
  .configure(defaultSettings)

lazy val scalaTest = (project in file("modules/scalatest"))
  .settings(
    name := "scalatest-embedinflux",
    libraryDependencies ++= Library.scalaTestDep
  )
  .configure(defaultSettings)
  .dependsOn(core)

lazy val specs2 = (project in file("modules/specs2"))
  .settings(
    name := "specs2-embedinflux",
    libraryDependencies ++= Library.specs2Dep,
    scalacOptions in Test ++= Seq("-Yrangepos")
  )
  .configure(defaultSettings)
  .dependsOn(core)

def defaultSettings: Project => Project =
  _.settings(Settings.common: _*)
    .settings(Settings.publish: _*)
    .settings(Settings.header)
    .enablePlugins(AutomateHeaderPlugin)


