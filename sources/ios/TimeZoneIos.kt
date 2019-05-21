package com.github.fluidsonic.fluid.time

import platform.Foundation.*


actual typealias Platform_TimeZone = NSTimeZone


internal actual fun Platform_TimeZone.daylightSavingTimeOffset(timestamp: Timestamp) =
	Seconds(daylightSavingTimeOffsetForDate(timestamp.toPlatform()).toLong())


internal actual val Platform_TimeZone.id: String get() = name


internal actual fun Platform_TimeZone.isDaylightSavingTime(timestamp: Timestamp) =
	isDaylightSavingTimeForDate(timestamp.toPlatform())


internal actual fun Platform_TimeZone.nextDaylightSavingTimeTransition(after: Timestamp) =
	nextDaylightSavingTimeTransitionAfterDate(after.toPlatform())?.toCommon()


@Suppress("UNCHECKED_CAST")
internal actual val platform_knownTimeZoneIds: Set<String> =
	(Platform_TimeZone.knownTimeZoneNames as List<String>).toSet()


internal actual val platform_systemTimeZoneId: String
	get() = Platform_TimeZone.systemTimeZone.id


internal actual fun platform_timeZoneWithId(id: String) =
	Platform_TimeZone.timeZoneWithName(id)
