package com.github.fluidsonic.fluid.time

import org.threeten.bp.DayOfWeek as PlatformDayOfWeek
import org.threeten.bp.LocalDate as PlatformLocalDate


actual val LocalDate.dayOfWeek: DayOfWeek
	get() = PlatformDayOfWeek.from(toPlatform()).toCommon()


actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp =
	toPlatform().atStartOfDay(timeZone.toPlatform()).toInstant().toCommon()


actual fun LocalDate.minusDays(daysToSubtract: Long): LocalDate =
	toPlatform().minusDays(daysToSubtract).toCommon()


actual fun LocalDate.plusDays(daysToAdd: Long): LocalDate =
	toPlatform().plusDays(daysToAdd).toCommon()


fun LocalDate.toPlatform(): PlatformLocalDate =
	PlatformLocalDate.of(year.toInt(), month.toInt(), day.toInt())


fun PlatformLocalDate.toCommon() =
	LocalDate.of(year = year.toLong(), month = monthValue.toLong(), day = dayOfMonth.toLong())
