import com.typesafe.sbt.SbtPgp.autoImportImpl.{pgpPassphrase, pgpPublicRing, pgpSecretRing, useGpg}
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.headerLicense
import de.heikoseeberger.sbtheader.License
import sbt.Keys._
import sbt.librarymanagement.LibraryManagementSyntax
import sbt.{Developer, Opts, ScmInfo, file, url}

object Settings extends LibraryManagementSyntax {
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
    parallelExecution in Test := false
  )

  val publish = List(
    useGpg := false,
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/fsanaulla/scala-embedinflux"),
        "scm:git@github.com:fsanaulla/scala-embedinflux.git"
      )
    ),
    pomIncludeRepository := (_ => false),
    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),
    publishArtifact in Test := false,
    pgpPublicRing := file("pubring.asc"),
    pgpSecretRing := file("secring.asc"),
    pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toCharArray)
  )

  lazy val header = headerLicense := Some(License.ALv2("2017-2019", Owner.name))
}
