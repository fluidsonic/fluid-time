package io.fluidsonic.time

import kotlinx.datetime.*


/** Parses an ISO-8601 date string, returning `null` on invalid input. */
public fun LocalDate.Companion.parseOrNull(isoString: String): LocalDate? =
	runCatching { parse(isoString) }.getOrNull()


/** Creates a [LocalDate] from the given components, returning `null` if the values are invalid. */
public fun LocalDateOrNull(year: Int, month: Int, day: Int): LocalDate? =
	runCatching { LocalDate(year, month, day) }.getOrNull()


/** Creates a [LocalDate] from the given components, returning `null` if the values are invalid. */
public fun LocalDateOrNull(year: Int, month: Month, day: Int): LocalDate? =
	runCatching { LocalDate(year, month, day) }.getOrNull()
