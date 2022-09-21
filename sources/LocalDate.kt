package io.fluidsonic.time

import kotlinx.datetime.*


public fun LocalDate.Companion.parseOrNull(isoString: String): LocalDate? =
	runCatching { parse(isoString) }.getOrNull()


public fun LocalDateOrNull(year: Int, month: Int, day: Int): LocalDate? =
	runCatching { LocalDate(year, month, day) }.getOrNull()


public fun LocalDateOrNull(year: Int, month: Month, day: Int): LocalDate? =
	runCatching { LocalDate(year, month, day) }.getOrNull()
