package com.github.fluidsonic.fluid.time


fun TimeZone.toPlatform() =
	platform.value


// not possible, see https://youtrack.jetbrains.com/issue/KT-31517
// actual typealias PlatformTimeZone = PlatformZoneId

actual class PlatformTimeZone(val value: PlatformZoneId)


internal actual fun PlatformTimeZone.daylightSavingTimeOffset(timestamp: Timestamp) =
	Seconds(value.rules.getDaylightSavings(timestamp.toPlatform()).seconds)


internal actual val PlatformTimeZone.id: String get() = value.id


internal actual fun PlatformTimeZone.isDaylightSavingTime(timestamp: Timestamp) =
	value.rules.isDaylightSavings(timestamp.toPlatform())


internal actual fun PlatformTimeZone.nextDaylightSavingTimeTransition(after: Timestamp) =
	value.rules.nextTransition(after.toPlatform())?.instant?.toCommon()


internal actual object PlatformTimeZoneStatic {

	actual val knownTimeZoneIds: Set<String> =
		PlatformZoneId.getAvailableZoneIds()


	actual val systemTimeZoneId: String
		get() = PlatformZoneId.systemDefault().id


	actual fun timeZoneWithId(id: String): PlatformTimeZone? =
		PlatformTimeZone(PlatformZoneId.of(id))
}
