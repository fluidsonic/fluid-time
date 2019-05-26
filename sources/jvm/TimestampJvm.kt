package com.github.fluidsonic.fluid.time

import org.threeten.bp.DayOfWeek as PlatformDayOfWeek
import org.threeten.bp.Instant as PlatformTimestamp


actual val Timestamp.dayOfWeek: DayOfWeek
	get() = PlatformDayOfWeek.from(toPlatform()).toCommon()


actual fun Timestamp.toLocalDate(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).toLocalDate().toCommon()


actual fun Timestamp.toLocalDateTime(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).toLocalDateTime().toCommon()


actual fun Timestamp.toLocalTime(timeZone: TimeZone) =
	toPlatform().atZone(timeZone.toPlatform()).toLocalTime().toCommon()


fun Timestamp.toPlatform(): PlatformTimestamp =
	PlatformTimestamp.ofEpochSecond(secondsSince1970.value, partialNanosecond.value)


fun PlatformTimestamp.toCommon() =
	Timestamp.of(secondsSince1970 = Seconds(epochSecond), nanoseconds = Nanoseconds(nano))
