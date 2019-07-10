package com.github.fluidsonic.fluid.time

import org.threeten.bp.Instant as PlatformTimestamp


actual fun Timestamp.toDayOfWeek(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).dayOfWeek.toCommon()


actual fun Timestamp.toLocalDate(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).toLocalDate().toCommon()


actual fun Timestamp.toLocalDateTime(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).toLocalDateTime().toCommon()


actual fun Timestamp.toLocalTime(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).toLocalTime().toCommon()


fun Timestamp.toPlatform(): PlatformTimestamp =
	PlatformTimestamp.ofEpochSecond(secondsSince1970.toLong(), partialNanosecond.toLong())


fun PlatformTimestamp.toCommon() =
	Timestamp.of(secondsSince1970 = Seconds(epochSecond), nanoseconds = Nanoseconds(nano))
