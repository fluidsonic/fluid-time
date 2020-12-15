package io.fluidsonic.time

import kotlinx.datetime.*


public val LocalDateTime.time: LocalTime
	get() = LocalTime(hour = hour, minute = minute, second = second, nanosecond = nanosecond)


// https://kotlinlang.slack.com/archives/C01923PC6A0/p1597788327006500
public fun LocalDateTime.toTimestamp(timeZone: TimeZone): Timestamp =
	toInstant(timeZone)


public fun LocalDateTime.Companion.parseOrNull(isoString: String): LocalDateTime? =
	runCatching { parse(isoString) }.getOrNull()
