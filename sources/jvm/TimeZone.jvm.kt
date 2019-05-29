package com.github.fluidsonic.fluid.time

import org.threeten.bp.*


fun TimeZone.toPlatform() =
	platform.value


// not possible, see https://youtrack.jetbrains.com/issue/KT-31517
// actual typealias Platform_TimeZone = ZoneId

actual class Platform_TimeZone(val value: ZoneId)


internal actual fun Platform_TimeZone.daylightSavingTimeOffset(timestamp: Timestamp) =
	Seconds(value.rules.getDaylightSavings(timestamp.toPlatform()).seconds)


internal actual val Platform_TimeZone.id: String get() = value.id


internal actual fun Platform_TimeZone.isDaylightSavingTime(timestamp: Timestamp) =
	value.rules.isDaylightSavings(timestamp.toPlatform())


internal actual fun Platform_TimeZone.nextDaylightSavingTimeTransition(after: Timestamp) =
	value.rules.nextTransition(after.toPlatform())?.instant?.toCommon()


internal actual object Platform_TimeZone_Static {

	actual val knownTimeZoneIds: Set<String> =
		ZoneId.getAvailableZoneIds()


	actual val systemTimeZoneId: String
		get() = ZoneId.systemDefault().id


	actual fun timeZoneWithId(id: String): Platform_TimeZone? =
		Platform_TimeZone(ZoneId.of(id))
}
