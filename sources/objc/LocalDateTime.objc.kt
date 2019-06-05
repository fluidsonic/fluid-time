package com.github.fluidsonic.fluid.time

import platform.Foundation.*


actual val LocalDateTime.dayOfWeek
	get() = atTimeZone(TimeZone.utc).toDayOfWeek(TimeZone.utc)


actual fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.timeZone = timeZone.platform

	return platform_gregorianCalendar.dateFromComponents(components)!!.toCommon()
}


internal fun LocalDateTime.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = date.year.value
	components.month = date.month.value
	components.day = date.day.value
	components.hour = time.hour.value
	components.minute = time.minute.value
	components.second = time.second.value
	components.nanosecond = time.nanosecond.value
	return components
}
