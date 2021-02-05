fluid-time
==========

[![Maven Central](https://img.shields.io/maven-central/v/io.fluidsonic.time/fluid-time?label=Maven%20Central)](https://search.maven.org/artifact/io.fluidsonic.time/fluid-time)
[![JCenter](https://img.shields.io/bintray/v/fluidsonic/kotlin/time?label=JCenter)](https://bintray.com/fluidsonic/kotlin/time)
[![Tests](https://github.com/fluidsonic/fluid-time/workflows/Tests/badge.svg)](https://github.com/fluidsonic/fluid-time/actions?workflow=Tests)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.30%20(Darwin,%20JS,%20JVM)-blue.svg)](https://github.com/JetBrains/kotlin/releases/v1.4.30)
[![kotlinx-datetime](https://img.shields.io/badge/kotlinx--datetime-0.1.1-blue.svg)](https://github.com/Kotlin/kotlinx-datetime/releases/tag/v0.1.1)
[![#fluid-libraries Slack Channel](https://img.shields.io/badge/slack-%23fluid--libraries-543951.svg?label=Slack)](https://kotlinlang.slack.com/messages/C7UDFSVT2/)

Additions for Kotlin's date & time library [`kotlinx-datetime`](https://github.com/Kotlin/kotlinx-datetime).

`kotlinx-datetime` is very early stage and not as actively developed as other official libraries. This library keeps adding missing pieces until the official
library catches up.



Installation
------------

`build.gradle.kts`:

```kotlin
dependencies {
	implementation("io.fluidsonic.time:fluid-time:0.13.1")
}
```

Additions
---------

- `LocalTime` - similar to the existing `LocalDate` and `LocalDateTime`
- `LocalTime.midnight` - `00:00`
- `LocalTime.atDate(LocalDate): LocalDateTime` - create `LocalDateTime` from `LocalDate` and `LocalTime`
- `LocalDate.atTime(LocalTime): LocalDateTime` - create `LocalDateTime` from `LocalDate` and `LocalTime`
- `LocalDateTime.time` - returns its time components as `LocalTime`
- `Timestamp` - alias of `Instant` for those who prefer that name ([Discussion](https://kotlinlang.slack.com/archives/C01923PC6A0/p1597788327006500))
- `(LocalDate|LocalDateTime|LocalTime|Timestamp).parseOrNull(String)` - like `parse()` but returns `null` instead of throwing
- `TimeZone.ofOrNull(String)` - like `of()` but returns `null` instead of throwing
- `(LocalDate|LocalDateTime|LocalTime|TimeStamp|TimeZone)Serializer` - for use with `kotlinx-serialization`
- `Timestamp.toLocalDate(TimeZone)` - shortcut for `.toLocalDateTime(timeZone).date`
- `Timestamp.toLocalTime(TimeZone)` - shortcut for `.toLocalDateTime(timeZone).time`
- `Timestamp.toJavaDate(): Date` - converts a `Timestamp` to `java.util.Date` (JVM only)
- `Month.daysIn(year)` - number of days in a given month and year
- `Year.isLeap(year)` - whether a year is a leap year
- `ManualClock` - a `Clock` implementation suitable for unit testing
- **JVM**, **JS** and **Darwin** are supported. `linuxX64` and `mingwX64` are not supported.

Previous library
----------------

Before `kotlinx-datetime` was released this library has provided its own date & time implementations for JVM and Darwin. That work has been discontinued in
favor of using the new official library, even if experimental, with additions provided by this reworked library. Bugfix releases will still be provided if
needed.

You can still use the last previous library [version 0.10.5](https://github.com/fluidsonic/fluid-time/releases/tag/0.10.5).


License
-------

Apache 2.0
