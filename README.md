# Scala Embedded InfluxDB
[![Build Status](https://travis-ci.org/fsanaulla/scala-embedinflux.svg?branch=master)](https://travis-ci.org/fsanaulla/scala-embedinflux)

Extensions for [ScalaTest](http://www.scalatest.org/) and [Specs2](https://etorreborre.github.io/specs2/), for simply embedding [InfluxDB](https://www.influxdata.com/time-series-platform/influxdb/) in your test.
Built on top of [embed-influxDB](https://github.com/APISENSE/embed-influxDB). 
Inspired by [scalatest-embedmongo](https://github.com/SimplyScala/scalatest-embedmongo).

## Integration
Add this dependencies to your `build.sbt`
```
// Scalates
libraryDependencies += "com.github.fsanaulla" %% "scalatest-embedinflux" % "0.1.0" % Test

// Specs2
libraryDependencies += "com.github.fsanaulla" %% "specs2-embedinflux" % "0.1.0" % Test
```
## Usage
Influx will start by default of HTTP port `8086`, with back up port `8088`. You can change this parameters by overriding `port` and `backUpPort` fields.
For embed InfluxDB in your test suite, mix it with `EmbeddedInfluxDB`.

For **Scalatest** it's look like:
```
class InfluxSpec extends FlatSpec with EmbeddedInfluxDB {

  lazy val influx: InfluxDBClient = _

  ... // your tests
}
```

For **Specs2** it's look the same:
```
class InfluxSpec extends mutable.Specification with EmbeddedInfluxDB {

  lazy val influx: InfluxDBClient = _
  
  ... // your tests
}
```
