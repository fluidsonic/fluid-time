package io.fluidsonic.time

import platform.Foundation.*


internal actual fun Period.Companion.between(startInclusive: LocalDate, endExclusive: LocalDate): Period {
	val components = platform_gregorianCalendar.components(
		unitFlags = NSDayCalendarUnit or NSMonthCalendarUnit or NSYearCalendarUnit,
		fromDate = startInclusive.atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		toDate = endExclusive.atStartOfDay(timeZone = TimeZone.utc).toPlatform(),
		options = 0UL
	)

	return Period(years = components.year, months = components.month, days = components.day)
}
