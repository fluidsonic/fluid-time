package io.fluidsonic.time

import kotlin.time.*
import kotlin.time.Duration.Companion.nanoseconds

private const val nanosecondsPerSecond = 1_000_000_000L
private const val nanosecondsPerMinute = nanosecondsPerSecond * 60
private const val nanosecondsPerHour = nanosecondsPerMinute * 60
private const val nanosecondsPerDay = nanosecondsPerHour * 24


// TODO Doesn't check for overflows. Improve.
public fun Duration(
	days: Int = 0,
	hours: Int = 0,
	minutes: Int = 0,
	seconds: Int = 0,
	nanoseconds: Int = 0,
): Duration = (
	(days * nanosecondsPerDay) +
		(hours * nanosecondsPerHour) +
		(minutes * nanosecondsPerMinute) +
		(seconds * nanosecondsPerSecond) +
		nanoseconds
	).nanoseconds
