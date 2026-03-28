package io.fluidsonic.time

import kotlinx.datetime.*


/** Converts this [LocalDateTime] to a [Timestamp] in the given [timeZone]. */
public fun LocalDateTime.toTimestamp(timeZone: TimeZone): Timestamp =
	toInstant(timeZone)


/** Parses an ISO-8601 date-time string, returning `null` on invalid input. */
public fun LocalDateTime.Companion.parseOrNull(isoString: String): LocalDateTime? =
	runCatching { parse(isoString) }.getOrNull()
