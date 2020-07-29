package io.fluidsonic.time

import platform.Foundation.*


public actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.hour = 0
	components.minute = 0
	components.second = 0
	components.nanosecond = 0
	components.timeZone = timeZone.platform

	return platform_gregorianCalendar.dateFromComponents(components)!!.toCommon()
}


// TODO Is this correct? We're changing both dates by one day here.
public actual fun LocalDate.daysSince(startExclusive: LocalDate): Days =
	Days(platform_gregorianCalendar.components(
		unitFlags = NSDayCalendarUnit,
		fromDate = startExclusive.atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL
	).day)


public actual fun LocalDate.daysUntil(endExclusive: LocalDate): Days =
	endExclusive.daysSince(this)


public actual operator fun LocalDate.plus(days: Days): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSDayCalendarUnit,
		value = days.toLong(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


public actual operator fun LocalDate.plus(months: Months): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSMonthCalendarUnit,
		value = months.toLong(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


public actual operator fun LocalDate.plus(years: Years): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSYearCalendarUnit,
		value = years.toLong(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


public actual fun LocalDate.toDayOfWeek(): DayOfWeek =
	atStartOfDay(TimeZone.utc).toDayOfWeek(TimeZone.utc)


internal fun LocalDate.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = year.toLong()
	components.month = month.toLong()
	components.day = day.toLong()
	return components
}
