package com.github.fluidsonic.fluid.time

import platform.Foundation.*


actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.hour = 0
	components.minute = 0
	components.second = 0
	components.nanosecond = 0
	components.timeZone = timeZone.platform

	return platform_gregorianCalendar.dateFromComponents(components)!!.toCommon()
}


actual fun LocalDate.minusDays(daysToSubtract: Long): LocalDate =
	plusDays(-daysToSubtract)


actual fun LocalDate.plusDays(daysToAdd: Long): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSDayCalendarUnit,
		value = daysToAdd,
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


actual fun LocalDate.toDayOfWeek() =
	atStartOfDay(TimeZone.utc).toDayOfWeek(TimeZone.utc)


internal fun LocalDate.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = year.toLong()
	components.month = month.toLong()
	components.day = day.toLong()
	return components
}
