package io.fluidsonic.time

import platform.Foundation.NSDate as PlatformTimestamp
import kotlin.math.*
import platform.Foundation.*


public actual fun Timestamp.toDayOfWeek(timeZone: TimeZone): DayOfWeek {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return when (val weekday = components.weekday) {
		1L.toNSInteger() -> DayOfWeek.sunday
		2L.toNSInteger() -> DayOfWeek.monday
		3L.toNSInteger() -> DayOfWeek.tuesday
		4L.toNSInteger() -> DayOfWeek.wednesday
		5L.toNSInteger() -> DayOfWeek.thursday
		6L.toNSInteger() -> DayOfWeek.friday
		7L.toNSInteger() -> DayOfWeek.saturday
		else -> error("unexpected weekday: $weekday")
	}
}


public actual fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalDate.of(
		year = components.year.toLong(),
		month = components.month.toLong(),
		day = components.day.toLong()
	)
}


public actual fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalDateTime.of(
		year = components.year.toLong(),
		month = components.month.toLong(),
		day = components.day.toLong(),
		hour = components.hour.toLong(),
		minute = components.minute.toLong(),
		second = components.second.toLong(),
		nanosecond = components.nanosecond.toLong()
	)
}


public actual fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalTime.of(
		hour = components.hour.toLong(),
		minute = components.minute.toLong(),
		second = components.second.toLong(),
		nanosecond = components.nanosecond.toLong()
	)
}


public fun Timestamp.toPlatform(): PlatformTimestamp =
	PlatformTimestamp.dateWithTimeIntervalSince1970(secondsSince1970.toLong().toDouble() + (partialNanosecond.toLong().toDouble() / 1_000_000_000.0))


public fun PlatformTimestamp.toCommon(): Timestamp {
	val secondsSince1970 = timeIntervalSince1970
	return Timestamp.of(
		secondsSince1970 = Seconds(secondsSince1970.toLong()),
		nanoseconds = Nanoseconds(((secondsSince1970 % 1) * Nanoseconds.perSecond.toLong()).roundToLong())
	)
}
