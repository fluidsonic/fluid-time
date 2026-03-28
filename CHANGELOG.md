# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com) and this project adheres to [Semantic Versioning](https://semver.org).

## [0.19.0] - 2026-03-28

### Changed
- Updated Kotlin from 1.8.22 to 2.3.20
- Updated Gradle from 8.1.1 to 9.4.1
- Updated kotlinx-datetime from 0.4.0 to 0.7.1
- Updated kotlinx-serialization from 1.5.1 to 1.10.0
- Updated fluid-gradle plugin from 1.3.1 to 3.0.0
- Replaced `error()` with `checkNotNull()` in `ManualClock` for clearer state validation
- Minimum JDK raised from 17 to 21

### Added
- KDoc documentation for all public API declarations
- Overflow checking in `Duration()` factory function
- Tests for `Clock.toJavaClock()`

### Removed
- Darwin/native target support (iOS, macOS, tvOS, watchOS)

### Deprecated
- JS target (following Kotlin/JS deprecation in Kotlin 2.3)

### Fixed
- Copy-paste bugs in `LocalDateTests`, `LocalDateTimeTests`, and `LocalTimeTests` where `Timestamp.parseOrNull` was tested instead of the correct type's `parseOrNull`
