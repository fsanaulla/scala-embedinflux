import com.typesafe.sbt.SbtPgp.autoImportImpl.useGpg
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.headerLicense
import de.heikoseeberger.sbtheader.License
import sbt.Keys._
import sbt.{Developer, Opts, ScmInfo, url}

object Settings {
  lazy val common = List(
    scalaVersion := "2.12.8",
    crossScalaVersions := Seq("2.11.8", scalaVersion.value),
    organization := "com.github.fsanaulla",
    homepage := Some(url("https://github.com/fsanaulla/scala-embedinflux")),
    licenses += "Apache-2.0" -> url("https://opensource.org/licenses/Apache-2.0"),
    developers += Developer(id = "fsanaulla", name = "Faiaz Sanaulla", email = "fayaz.sanaulla@gmail.com", url = url("https://github.com/fsanaulla")),
    parallelExecution := false
  )

  lazy val publish = List(
    useGpg := true,
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

  lazy val header = headerLicense := Some(License.ALv2("2017-2019", "Faiaz Sanaulla"))
}
