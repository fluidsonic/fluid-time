package io.fluidsonic.time

import platform.Foundation.*


public fun TimeZone.toPlatform(): PlatformTimeZone =
	platform


public actual typealias PlatformTimeZone = NSTimeZone


internal actual fun PlatformTimeZone.daylightSavingTimeOffset(timestamp: Timestamp) =
	Seconds(daylightSavingTimeOffsetForDate(timestamp.toPlatform()).toLong())


internal actual val PlatformTimeZone.id: String
	get() =
		name.let { id ->
			if (id == "GMT") "UTC" else id
		}


internal actual fun PlatformTimeZone.isDaylightSavingTime(timestamp: Timestamp) =
	isDaylightSavingTimeForDate(timestamp.toPlatform())


internal actual fun PlatformTimeZone.nextDaylightSavingTimeTransition(after: Timestamp) =
	nextDaylightSavingTimeTransitionAfterDate(after.toPlatform())?.toCommon()


internal actual object PlatformTimeZoneStatic {

	@Suppress("UNCHECKED_CAST")
	actual val knownTimeZoneIds: Set<String> =
		(PlatformTimeZone.knownTimeZoneNames as List<String>).toSet()


	actual val systemTimeZoneId: String
		get() = PlatformTimeZone.systemTimeZone.id


	actual fun timeZoneWithId(id: String) =
		PlatformTimeZone.timeZoneWithName(id)
}
