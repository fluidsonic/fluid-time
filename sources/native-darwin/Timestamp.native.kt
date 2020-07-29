package io.fluidsonic.time

import kotlin.math.*
import platform.Foundation.*
import platform.Foundation.NSDate as PlatformTimestamp


public actual fun Timestamp.toDayOfWeek(timeZone: TimeZone): DayOfWeek {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return when (val weekday = components.weekday) {
		1L -> DayOfWeek.sunday
		2L -> DayOfWeek.monday
		3L -> DayOfWeek.tuesday
		4L -> DayOfWeek.wednesday
		5L -> DayOfWeek.thursday
		6L -> DayOfWeek.friday
		7L -> DayOfWeek.saturday
		else -> error("unexpected weekday: $weekday")
	}
}


public actual fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalDate.of(
		year = components.year,
		month = components.month,
		day = components.day
	)
}


public actual fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalDateTime.of(
		year = components.year,
		month = components.month,
		day = components.day,
		hour = components.hour,
		minute = components.minute,
		second = components.second,
		nanosecond = components.nanosecond
	)
}


public actual fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalTime.of(
		hour = components.hour,
		minute = components.minute,
		second = components.second,
		nanosecond = components.nanosecond
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
