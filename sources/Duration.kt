package io.fluidsonic.time

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

private const val nanosecondsPerSecond = 1_000_000_000L
private const val nanosecondsPerMinute = nanosecondsPerSecond * 60
private const val nanosecondsPerHour = nanosecondsPerMinute * 60
private const val nanosecondsPerDay = nanosecondsPerHour * 24


/** Creates a [Duration] from the given time components. Throws [IllegalArgumentException] on overflow. */
public fun Duration(
	days: Int = 0,
	hours: Int = 0,
	minutes: Int = 0,
	seconds: Int = 0,
	nanoseconds: Int = 0,
): Duration {
	val totalNanoseconds = addExactOrNull(
		multiplyExactOrNull(days.toLong(), nanosecondsPerDay)
			?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
		addExactOrNull(
			multiplyExactOrNull(hours.toLong(), nanosecondsPerHour)
				?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
			addExactOrNull(
				multiplyExactOrNull(minutes.toLong(), nanosecondsPerMinute)
					?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
				addExactOrNull(
					multiplyExactOrNull(seconds.toLong(), nanosecondsPerSecond)
						?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
					nanoseconds.toLong(),
				) ?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
			) ?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
		) ?: overflowDuration(days, hours, minutes, seconds, nanoseconds),
	) ?: overflowDuration(days, hours, minutes, seconds, nanoseconds)

	return totalNanoseconds.nanoseconds
}


private fun overflowDuration(days: Int, hours: Int, minutes: Int, seconds: Int, nanoseconds: Int): Nothing =
	throw IllegalArgumentException(
		"Duration overflow: the combination of days=$days, hours=$hours, minutes=$minutes, seconds=$seconds, nanoseconds=$nanoseconds exceeds the supported range."
	)


private fun addExactOrNull(a: Long, b: Long): Long? {
	val result = a + b
	if (a xor b < 0 || a xor result >= 0) return result
	return null
}


private fun multiplyExactOrNull(a: Long, b: Long): Long? {
	val result = a * b
	if (a == 0L || b == 0L) return result
	if (result / a != b) return null
	return result
}
