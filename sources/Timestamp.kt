package io.fluidsonic.time

import kotlin.time.Duration
import kotlin.time.Instant
import kotlinx.datetime.*


/** Type alias for [Instant], representing a point on the UTC timeline. */
public typealias Timestamp = Instant


/** Returns the duration elapsed since [other] (positive if this timestamp is after [other]). */
public fun Timestamp.durationSince(other: Timestamp): Duration =
	this - other


/** Returns the duration from this timestamp until [other] (positive if [other] is after this). */
public fun Timestamp.durationUntil(other: Timestamp): Duration =
	other - this


/** Parses an ISO-8601 instant string, returning `null` on invalid input. */
public fun Instant.Companion.parseOrNull(isoString: String): Timestamp? =
	runCatching { parse(isoString) }.getOrNull()


/** Converts this timestamp to a [LocalDate] in the given [timeZone]. */
public fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate =
	toLocalDateTime(timeZone).date


/** Converts this timestamp to a [LocalTime] in the given [timeZone]. */
public fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime =
	toLocalDateTime(timeZone).time
