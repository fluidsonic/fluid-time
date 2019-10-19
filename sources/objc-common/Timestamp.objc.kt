package io.fluidsonic.time

import platform.Foundation.*
import kotlin.math.*
import platform.Foundation.NSDate as PlatformTimestamp


actual fun Timestamp.toDayOfWeek(timeZone: TimeZone): DayOfWeek {
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


actual fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalDate.of(
		year = components.year,
		month = components.month,
		day = components.day
	)
}


actual fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime {
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


actual fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime {
	val components = platform_gregorianCalendar.componentsInTimeZone(timezone = timeZone.platform, fromDate = toPlatform())
	return LocalTime.of(
		hour = components.hour,
		minute = components.minute,
		second = components.second,
		nanosecond = components.nanosecond
	)
}


fun Timestamp.toPlatform() =
	PlatformTimestamp.dateWithTimeIntervalSince1970(secondsSince1970.toLong().toDouble() + (partialNanosecond.toLong().toDouble() / 1_000_000_000.0))


fun PlatformTimestamp.toCommon(): Timestamp {
	val secondsSince1970 = timeIntervalSince1970
	return Timestamp.of(
		secondsSince1970 = Seconds(secondsSince1970.toLong()),
		nanoseconds = Nanoseconds(((secondsSince1970 % 1) * Nanoseconds.perSecond.toLong()).roundToLong())
	)
}
