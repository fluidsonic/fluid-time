package io.fluidsonic.time

import platform.Foundation.*


internal actual fun Period.Companion.between(startInclusive: LocalDate, endExclusive: LocalDate): Period {
	val components = platform_gregorianCalendar.components(
		unitFlags = NSDayCalendarUnit or NSMonthCalendarUnit or NSYearCalendarUnit,
		fromDate = startInclusive.atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		toDate = endExclusive.atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL.toNSUInteger()
	)

	return Period(years = components.year.toLong(), months = components.month.toLong(), days = components.day.toLong())
}
