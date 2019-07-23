import com.typesafe.sbt.SbtPgp.autoImportImpl.useGpg
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.headerLicense
import de.heikoseeberger.sbtheader.License
import sbt.Keys._
import sbt.{Developer, Opts, ScmInfo, url}

object Settings {
  private object Owner {
    val id           = "fsanaulla"
    val name         = "Faiaz Sanaulla"
    val email        = "fayaz.sanaulla@gmail.com"
    val github       = "https://github.com/fsanaulla"
    val organisation = "com.github.fsanaulla"
  }

  lazy val common = List(
    scalaVersion := "2.12.8",
    crossScalaVersions := Seq("2.11.8", scalaVersion.value),
    organization := Owner.organisation,
    homepage := Some(url("https://github.com/fsanaulla/scala-embedinflux")),
    licenses += "Apache-2.0" -> url("https://opensource.org/licenses/Apache-2.0"),
    developers += Developer(Owner.id, Owner.name, Owner.email, url = url(Owner.github)),
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

  lazy val header = headerLicense := Some(License.ALv2("2017-2019", Owner.name))
}
