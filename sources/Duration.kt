package io.fluidsonic.time

import kotlin.time.*
import kotlin.time.Duration.Companion.nanoseconds


private val iso8601Regex = Regex(
	"([-+]?)P(?:([-+]?\\d+)D)?(T(?:([-+]?\\d+)H)?(?:([-+]?\\d+)M)?(?:([-+]?\\d+)(?:[.,](\\d{0,9}))?S)?)?",
	RegexOption.IGNORE_CASE
)

private const val nanosecondsPerSecond = 1_000_000_000L
private const val nanosecondsPerMinute = nanosecondsPerSecond * 60
private const val nanosecondsPerHour = nanosecondsPerMinute * 60
private const val nanosecondsPerDay = nanosecondsPerHour * 24


// TODO Doesn't check for overflows. Improve.
@ExperimentalTime
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


@Deprecated(
	message = "Use the new Duration.parseIsoString(String)",
	level = DeprecationLevel.ERROR,
	replaceWith = ReplaceWith(expression = "Duration.parse(isoString)", "kotlin.time.Duration"),
)
@ExperimentalTime
public fun Duration.Companion.parse(isoString: CharSequence): Duration =
	parseIsoString(isoString.toString())


@Deprecated(
	message = "Use the new Duration.parseIsoStringOrNull(String)",
	level = DeprecationLevel.ERROR,
	replaceWith = ReplaceWith(expression = "Duration.parseOrNull(isoString)", "kotlin.time.Duration"),
)
@ExperimentalTime
public fun Duration.Companion.parseOrNull(isoString: CharSequence): Duration? =
	parseIsoStringOrNull(isoString.toString())
