package com.github.fluidsonic.fluid.time

import platform.Foundation.*


actual typealias Platform_TimeZone = NSTimeZone


internal actual fun Platform_TimeZone.daylightSavingTimeOffset(timestamp: Timestamp) =
	Seconds(daylightSavingTimeOffsetForDate(timestamp.toPlatform()).toLong())


internal actual val Platform_TimeZone.id: String
	get() =
		name.let { id ->
			if (id == "GMT") "UTC" else id
		}


internal actual fun Platform_TimeZone.isDaylightSavingTime(timestamp: Timestamp) =
	isDaylightSavingTimeForDate(timestamp.toPlatform())


internal actual fun Platform_TimeZone.nextDaylightSavingTimeTransition(after: Timestamp) =
	nextDaylightSavingTimeTransitionAfterDate(after.toPlatform())?.toCommon()


internal actual object Platform_TimeZone_Static {

	@Suppress("UNCHECKED_CAST")
	actual val knownTimeZoneIds: Set<String> =
		(Platform_TimeZone.knownTimeZoneNames as List<String>).toSet()


	actual val systemTimeZoneId: String
		get() = Platform_TimeZone.systemTimeZone.id


	actual fun timeZoneWithId(id: String) =
		Platform_TimeZone.timeZoneWithName(id)
}
