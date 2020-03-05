package io.fluidsonic.time


actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp =
	toPlatform().atStartOfDay(timeZone.toPlatform()).toInstant().toCommon()


actual fun LocalDate.daysSince(startExclusive: LocalDate): Days =
	startExclusive.daysUntil(this) // TODO Is this correct? We're changing both dates by one day here.


actual fun LocalDate.daysUntil(endExclusive: LocalDate): Days =
	Days(toPlatform().until(endExclusive.toPlatform(), PlatformChronoUnit.DAYS))


actual fun LocalDate.toDayOfWeek() =
	PlatformDayOfWeek.from(toPlatform()).toCommon()


actual operator fun LocalDate.plus(days: Days) =
	toPlatform().plusDays(days.toLong()).toCommon()


actual operator fun LocalDate.plus(months: Months) =
	toPlatform().plusMonths(months.toLong()).toCommon()


actual operator fun LocalDate.plus(years: Years) =
	toPlatform().plusYears(years.toLong()).toCommon()


fun LocalDate.toPlatform(): PlatformLocalDate =
	PlatformLocalDate.of(year.toInt(), month.toInt(), day.toInt())


fun PlatformLocalDate.toCommon() =
	LocalDate.of(year = year.toLong(), month = monthValue.toLong(), day = dayOfMonth.toLong())
