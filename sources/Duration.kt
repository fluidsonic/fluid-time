package io.fluidsonic.time

import kotlin.time.*


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
): Duration = Duration.nanoseconds(
	(days * nanosecondsPerDay) +
		(hours * nanosecondsPerHour) +
		(minutes * nanosecondsPerMinute) +
		(seconds * nanosecondsPerSecond) +
		nanoseconds
)


@ExperimentalTime
public fun Duration.Companion.parse(isoString: CharSequence): Duration =
	requireNotNull(parseOrNull(isoString)) { "Invalid ISO-8601 duration: $isoString" }


@ExperimentalTime
public fun Duration.Companion.parseOrNull(isoString: CharSequence): Duration? {
	val result = iso8601Regex.matchEntire(isoString) ?: return null // no match
	if (result.groupValues[3] == "T") return null // empty time

	val daysText = result.groupValues[2]
	val hoursText = result.groupValues[4]
	val minutesText = result.groupValues[5]
	val secondsText = result.groupValues[6]
	if (daysText.isEmpty() && hoursText.isEmpty() && minutesText.isEmpty() && secondsText.isEmpty()) return null // empty time

	val secondFractionString = result.groupValues[7]

	// TODO May throw for large values but should return null instead. Be careful to not introduce boxing when improving.
	val multiplier = if (result.groupValues[1] == "-") -1 else 1
	val days = parseNumber(daysText, multiplier = multiplier)
	val hours = parseNumber(hoursText, multiplier = multiplier)
	val minutes = parseNumber(minutesText, multiplier = multiplier)
	val seconds = parseNumber(secondsText, multiplier = multiplier)
	val nanoseconds = parseFraction(secondFractionString) * if (seconds < 0) -1 else 1

	// TODO May throw for large values but should return null instead.
	return Duration(days = days, hours = hours, minutes = minutes, seconds = seconds, nanoseconds = nanoseconds)
}


private fun parseFraction(text: String): Int {
	if (text.isEmpty()) return 0

	var fraction = text.toInt()
	for (i in text.length until 9)
		fraction *= 10

	return fraction
}


private fun parseNumber(text: String, multiplier: Int): Int {
	if (text.isEmpty()) return 0

	return text.toInt() * multiplier
}
