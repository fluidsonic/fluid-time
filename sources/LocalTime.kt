package io.fluidsonic.time

import kotlinx.datetime.*


private val _max = LocalTime(23, 59, 59, 999_999_999)
private val _min = LocalTime(0, 0)


/** The latest possible time of day (23:59:59.999999999). */
public val LocalTime.Companion.max: LocalTime
	get() = _max


/** Midnight (00:00). */
public val LocalTime.Companion.midnight: LocalTime
	get() = _min


/** The earliest possible time of day (00:00). */
public val LocalTime.Companion.min: LocalTime
	get() = _min


/** Parses an ISO-8601 time string, returning `null` on invalid input. */
public fun LocalTime.Companion.parseOrNull(isoString: String): LocalTime? =
	runCatching { parse(isoString) }.getOrNull()
