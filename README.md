# scala-embedinflux
[![Build Status](https://travis-ci.org/fsanaulla/scala-embedinflux.svg?branch=master)](https://travis-ci.org/fsanaulla/scala-embedinflux)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e9932125a5d2487f901e3170a70f4904)](https://www.codacy.com/app/fsanaulla/scala-embedinflux?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fsanaulla/scala-embedinflux&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.fsanaulla/core-testing_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.fsanaulla/core-testing_2.11)
# About project
Open-source library for [ScalaTest](http://www.scalatest.org/) and [Specs2](https://etorreborre.github.io/specs2/), for simply embedding [InfluxDB](https://www.influxdata.com/time-series-platform/influxdb/) in your test.
Built on top of [embed-influxDB](https://github.com/APISENSE/embed-influxDB). 
Inspired by [scalatest-embedmongo](https://github.com/SimplyScala/scalatest-embedmongo).

## Integration
Add this dependencies to your `build.sbt`
```
// Scalatest
libraryDependencies += "com.github.fsanaulla" %% "scalatest-embedinflux" % <version> % Test

// Specs2
libraryDependencies += "com.github.fsanaulla" %% "specs2-embedinflux" % <version> % Test
```
## Usage
Before you start your testing you need to choose, what type of configuration you need. For example if you need to work with [HTTP](https://docs.influxdata.com/influxdb/v1.5/guides/writing_data/) service.
Mix your test suite with `InfluxHttpConf`. For using [UDP](https://github.com/influxdata/influxdb/blob/master/services/udp/README.md) feature mix suite with `InfluxUDPConf`.
All port configuration can be changed. By overriding appropriate fields.

### HTTP
For starting InfluxDB with HTTP service:
```
// ScalaTest
class InfluxSpec extends FlatSpec with InfluxHTTPConf with EmbeddedInfluxDB {

  // by default `httpPort`: 8086
  // def httpPort: Int = 8086

  // auth is disabled(false)
  // def auth: Boolean = false

  lazy val influx: InfluxDBClient = _

  ... // your tests
}
```

```
// Specs2
class InfluxSpec extends mutable.Specification with InfluxHTTPConf with EmbeddedInfluxDB {

  // by default `httpPort`: 8086
  // def httpPort: Int = 8086

  // auth is disabled(false)
  // def auth: Boolean = false

  lazy val influx: InfluxDBClient = _

  ... // your tests
}
```
### UDP

For UDP support in your tests:
```
// ScalaTest
class InfluxSpec extends FlatSpec with InfluxUDPConf with EmbeddedInfluxDB {

  // by default `httpPort`: 8086
  // def httpPort: Int = 8086

  // by default `udpPort`: 8089
  // def udpPort: Int = 8089

  // default database name `udp`
  // def databse: Boolean = false

  lazy val influx: InfluxDBClient = _

  ... // your tests
}
```

```
// Specs2
class InfluxSpec extends mutable.Specification with InfluxUDPConf with EmbeddedInfluxDB {

  // by default `httpPort`: 8086
  // def httpPort: Int = 8086

  // by default `udpPort`: 8089
  // def udpPort: Int = 8089

  // default database name `udp`
  // def databse: Boolean = false

  lazy val influx: InfluxDBClient = _

  ... // your tests
}
```
### Custom
You can define custom configuration by mixin with `InfluxConf` into your test suite and overriding `configuration` method.
How to properly configure it look [here](https://github.com/APISENSE/embed-influxDB/blob/develop/src/main/java/io/apisense/embed/influx/configuration/InfluxConfigurationWriter.java).




