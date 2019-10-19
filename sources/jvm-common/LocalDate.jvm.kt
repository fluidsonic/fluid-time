package io.fluidsonic.time


actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp =
	toPlatform().atStartOfDay(timeZone.toPlatform()).toInstant().toCommon()


actual fun LocalDate.toDayOfWeek() =
	PlatformDayOfWeek.from(toPlatform()).toCommon()


actual fun LocalDate.minusDays(daysToSubtract: Long): LocalDate =
	toPlatform().minusDays(daysToSubtract).toCommon()


fun LocalDate.toPlatform(): PlatformLocalDate =
	PlatformLocalDate.of(year.toInt(), month.toInt(), day.toInt())


actual fun LocalDate.plusDays(daysToAdd: Long): LocalDate =
	toPlatform().plusDays(daysToAdd).toCommon()


fun PlatformLocalDate.toCommon() =
	LocalDate.of(year = year.toLong(), month = monthValue.toLong(), day = dayOfMonth.toLong())
