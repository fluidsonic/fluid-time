package io.fluidsonic.time

import platform.Foundation.*


public actual fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.timeZone = timeZone.platform

	return platform_gregorianCalendar.dateFromComponents(components)!!.toCommon()
}


public actual fun LocalDateTime.toDayOfWeek(): DayOfWeek =
	atTimeZone(TimeZone.utc).toDayOfWeek(TimeZone.utc)


internal fun LocalDateTime.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = date.year.toLong().toNSInteger()
	components.month = date.month.toLong().toNSInteger()
	components.day = date.day.toLong().toNSInteger()
	components.hour = time.hour.toLong().toNSInteger()
	components.minute = time.minute.toLong().toNSInteger()
	components.second = time.second.toLong().toNSInteger()
	components.nanosecond = time.nanosecond.toLong().toNSInteger()
	return components
}
