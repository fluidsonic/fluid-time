package io.fluidsonic.time


fun Period.toPlatform(): PlatformPeriod =
	PlatformPeriod.of(years.toInt(), months.toInt(), days.toInt())


fun PlatformPeriod.toCommon(): Period =
	Period(years = years, months = months, days = days)


internal actual fun Period.Companion.between(startInclusive: LocalDate, endExclusive: LocalDate): Period =
	PlatformPeriod.between(startInclusive.toPlatform(), endExclusive.toPlatform()).toCommon()
