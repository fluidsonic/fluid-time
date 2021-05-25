package io.fluidsonic.time

import kotlin.time.*
import kotlinx.datetime.*


// https://kotlinlang.slack.com/archives/C01923PC6A0/p1597788327006500
public typealias Timestamp = Instant


@ExperimentalTime
public fun Timestamp.durationSince(other: Timestamp): Duration =
	this - other


@ExperimentalTime
public fun Timestamp.durationUntil(other: Timestamp): Duration =
	other - this


public fun Instant.Companion.parseOrNull(isoString: String): Timestamp? =
	runCatching { parse(isoString) }.getOrNull()


public fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate =
	toLocalDateTime(timeZone).date


public fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime =
	toLocalDateTime(timeZone).time
