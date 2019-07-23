// build all project in one task, for combining coverage reports and decreasing CI jobs
addCommandAlias(
  "travisTest",
  ";project scalaTest;++ $TRAVIS_SCALA_VERSION fullTest;" +
    "project specs2;++ $TRAVIS_SCALA_VERSION fullTest"
)

addCommandAlias("fullTest", ";clean;compile;test:compile;coverage;test;coverageReport")