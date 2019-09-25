fluid-time
==========

[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.50-blue.svg)](http://kotlinlang.org)
[![#fluid-libraries Slack Channel](https://img.shields.io/badge/slack-%23fluid--libraries-543951.svg)](https://kotlinlang.slack.com/messages/C7UDFSVT2/)

Multiplatform date & time library.  
Very early stage.



Installation
------------

This library is [available on Bintray](https://bintray.com/fluidsonic/maven/fluid-time) as `fluid-time` in the group `com.github.fluidsonic`.

`build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.github.fluidsonic:fluid-time:0.9.12")
}

repositories {
    maven("https://dl.bintray.com/fluidsonic/maven")
}
```



To-Do
-----

- [ ] Check all FIXMEs
- [ ] Check for overflows
- [ ] Add more functionality
- [ ] Add more platforms
- [ ] Replace platform-specific dependencies with own implementation



License
-------

Apache 2.0
