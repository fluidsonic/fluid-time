package io.fluidsonic.time

import platform.Foundation.*


// TODO Do we have to consider any time zone rules here? If not then make common and remove special handling for JVM.
public actual fun LocalDate.atEndOfDay(timeZone: TimeZone): Timestamp =
	atTime(LocalTime.max).atTimeZone(timeZone)


public actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.hour = 0.toNSInteger()
	components.minute = 0.toNSInteger()
	components.second = 0.toNSInteger()
	components.nanosecond = 0.toNSInteger()
	components.timeZone = timeZone.platform

	return platform_gregorianCalendar.dateFromComponents(components)!!.toCommon()
}


// TODO Is this correct? We're changing both dates by one day here.
public actual fun LocalDate.daysSince(startExclusive: LocalDate): Days =
	Days(platform_gregorianCalendar.components(
		unitFlags = NSDayCalendarUnit,
		fromDate = startExclusive.atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL.toNSUInteger()
	).day.toLong())


public actual fun LocalDate.daysUntil(endExclusive: LocalDate): Days =
	endExclusive.daysSince(this)


public actual operator fun LocalDate.plus(days: Days): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSDayCalendarUnit,
		value = days.toLong().toNSInteger(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL.toNSUInteger()
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


public actual operator fun LocalDate.plus(months: Months): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSMonthCalendarUnit,
		value = months.toLong().toNSInteger(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL.toNSUInteger()
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


public actual operator fun LocalDate.plus(years: Years): LocalDate =
	platform_gregorianCalendar.dateByAddingUnit(
		NSYearCalendarUnit,
		value = years.toLong().toNSInteger(),
		toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL.toNSUInteger()
	)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


public actual fun LocalDate.toDayOfWeek(): DayOfWeek =
	atStartOfDay(TimeZone.utc).toDayOfWeek(TimeZone.utc)


internal fun LocalDate.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = year.toLong().toNSInteger()
	components.month = month.toLong().toNSInteger()
	components.day = day.toLong().toNSInteger()
	return components
}
