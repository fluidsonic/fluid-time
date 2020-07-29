package io.fluidsonic.time


public actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp =
	toPlatform().atStartOfDay(timeZone.toPlatform()).toInstant().toCommon()


public actual fun LocalDate.daysSince(startExclusive: LocalDate): Days =
	startExclusive.daysUntil(this) // TODO Is this correct? We're changing both dates by one day here.


public actual fun LocalDate.daysUntil(endExclusive: LocalDate): Days =
	Days(toPlatform().until(endExclusive.toPlatform(), PlatformChronoUnit.DAYS))


public actual fun LocalDate.toDayOfWeek(): DayOfWeek =
	PlatformDayOfWeek.from(toPlatform()).toCommon()


public actual operator fun LocalDate.plus(days: Days): LocalDate =
	toPlatform().plusDays(days.toLong()).toCommon()


public actual operator fun LocalDate.plus(months: Months): LocalDate =
	toPlatform().plusMonths(months.toLong()).toCommon()


public actual operator fun LocalDate.plus(years: Years): LocalDate =
	toPlatform().plusYears(years.toLong()).toCommon()


public fun LocalDate.toPlatform(): PlatformLocalDate =
	PlatformLocalDate.of(year.toInt(), month.toInt(), day.toInt())


public fun PlatformLocalDate.toCommon(): LocalDate =
	LocalDate.of(year = year.toLong(), month = monthValue.toLong(), day = dayOfMonth.toLong())
