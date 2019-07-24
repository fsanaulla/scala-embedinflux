ghreleaseRepoOrg := "fsanaulla"
ghreleaseRepoName := "scala-embedinflux"

ghreleaseAssets := Seq.empty

ghreleaseNotes := { tagName =>
  val version = tagName.stripPrefix("v")
  IO.read(baseDirectory.value / "changelog" / s"$version.md")
}