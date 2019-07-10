package com.github.fluidsonic.fluid.time

import kotlinx.serialization.*
import kotlinx.serialization.internal.*
import kotlin.math.*


// TODO handle overflows
@Serializable(with = DurationSerializer::class)
class Duration private constructor(
	val seconds: Seconds,
	val partialNanoseconds: Nanoseconds
) : TimeMeasurement<Duration> {

	init {
		freeze()
	}


	override val absolute
		get() = if (isNegative) -this else this


	override fun compareTo(other: Duration): Int {
		var result = seconds.compareTo(other.seconds)
		if (result == 0)
			result = partialNanoseconds.compareTo(other.partialNanoseconds)

		return result
	}


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		when (other) {
			0L -> throw ArithmeticException("/ by zero")
			1L -> this
			-1L -> -this
			else -> unchecked(seconds = seconds / other, partialNanoseconds = partialNanoseconds / other) // TODO probably not correct
		}


	override fun div(other: Duration): Long =
		TODO("how to implement this?")


	override fun equals(other: Any?) =
		this === other || (
			other is Duration
				&& seconds == other.seconds
				&& partialNanoseconds == other.partialNanoseconds
			)


	override fun hashCode() =
		seconds.hashCode() xor partialNanoseconds.hashCode()


	override val isNegative
		get() = seconds.isNegative || partialNanoseconds.isNegative


	override val isZero
		get() = (seconds.toLong() or partialNanoseconds.toLong()) == 0L


	override operator fun minus(other: Duration) =
		when {
			isZero -> -other
			other.isZero -> this
			else -> plus(seconds = -other.seconds, nanoseconds = -other.partialNanoseconds)
		}


	fun minus(
		days: Int = 0,
		hours: Int = 0,
		minutes: Int = 0,
		seconds: Int = 0,
		milliseconds: Int = 0,
		microseconds: Int = 0,
		nanoseconds: Int = 0
	) =
		minus(
			days = days.toLong(),
			hours = hours.toLong(),
			minutes = minutes.toLong(),
			seconds = seconds.toLong(),
			milliseconds = milliseconds.toLong(),
			microseconds = microseconds.toLong(),
			nanoseconds = nanoseconds.toLong()
		)


	fun minus(
		days: Long = 0,
		hours: Long = 0,
		minutes: Long = 0,
		seconds: Long = 0,
		milliseconds: Long = 0,
		microseconds: Long = 0,
		nanoseconds: Long = 0
	) =
		minus(
			days = Days(days),
			hours = Hours(hours),
			minutes = Minutes(minutes),
			seconds = Seconds(seconds),
			milliseconds = Milliseconds(milliseconds),
			microseconds = Microseconds(microseconds),
			nanoseconds = Nanoseconds(nanoseconds)
		)


	fun minus(
		days: Days = Days.zero,
		hours: Hours = Hours.zero,
		minutes: Minutes = Minutes.zero,
		seconds: Seconds = Seconds.zero,
		milliseconds: Milliseconds = Milliseconds.zero,
		microseconds: Microseconds = Microseconds.zero,
		nanoseconds: Nanoseconds = Nanoseconds.zero
	) =
		plus(
			days = -days,
			hours = -hours,
			minutes = -minutes,
			seconds = -seconds,
			milliseconds = -milliseconds,
			microseconds = -microseconds,
			nanoseconds = -nanoseconds
		)


	override operator fun plus(other: Duration) =
		when {
			isZero -> other
			other.isZero -> this
			else -> plus(seconds = other.seconds, nanoseconds = other.partialNanoseconds)
		}


	fun plus(
		days: Int = 0,
		hours: Int = 0,
		minutes: Int = 0,
		seconds: Int = 0,
		milliseconds: Int = 0,
		microseconds: Int = 0,
		nanoseconds: Int = 0
	) =
		plus(
			days = days.toLong(),
			hours = hours.toLong(),
			minutes = minutes.toLong(),
			seconds = seconds.toLong(),
			milliseconds = milliseconds.toLong(),
			microseconds = microseconds.toLong(),
			nanoseconds = nanoseconds.toLong()
		)


	fun plus(
		days: Long = 0,
		hours: Long = 0,
		minutes: Long = 0,
		seconds: Long = 0,
		milliseconds: Long = 0,
		microseconds: Long = 0,
		nanoseconds: Long = 0
	) =
		plus(
			days = Days(days),
			hours = Hours(hours),
			minutes = Minutes(minutes),
			seconds = Seconds(seconds),
			milliseconds = Milliseconds(milliseconds),
			microseconds = Microseconds(microseconds),
			nanoseconds = Nanoseconds(nanoseconds)
		)


	fun plus(
		days: Days = Days.zero,
		hours: Hours = Hours.zero,
		minutes: Minutes = Minutes.zero,
		seconds: Seconds = Seconds.zero,
		milliseconds: Milliseconds = Milliseconds.zero,
		microseconds: Microseconds = Microseconds.zero,
		nanoseconds: Nanoseconds = Nanoseconds.zero
	): Duration {
		if ((days.toLong() or
				hours.toLong() or
				minutes.toLong() or
				seconds.toLong() or
				milliseconds.toLong() or
				microseconds.toLong() or
				nanoseconds.toLong()) == 0L
		) return this

		return of(
			days = days,
			hours = hours,
			minutes = minutes,
			seconds = this.seconds + seconds,
			milliseconds = milliseconds,
			microseconds = microseconds,
			nanoseconds = this.partialNanoseconds + nanoseconds
		)
	}


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		when (other) {
			0L -> throw ArithmeticException("/ by zero")
			1L, -1L -> zero
			else -> TODO("how to implement this?")
		}


	override fun rem(other: Duration): Duration =
		TODO("how to implement this?")


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		when (other) {
			0L -> zero
			1L -> this
			-1L -> unchecked(seconds = -seconds, partialNanoseconds = -partialNanoseconds)
			else -> of(seconds = seconds * other, nanoseconds = partialNanoseconds * other)
		}


	override fun toDays() =
		seconds.toDays()


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override fun toDuration() =
		this


	override fun toHours() =
		seconds.toHours()


	override fun toMicroseconds() =
		seconds.toMicroseconds() + partialNanoseconds.toMicroseconds()


	override fun toMilliseconds() =
		seconds.toMilliseconds() + partialNanoseconds.toMilliseconds()


	override fun toNanoseconds() =
		seconds.toNanoseconds() + partialNanoseconds


	override fun toMinutes() =
		seconds.toMinutes()


	override fun toSeconds() =
		seconds


	override fun toString(): String {
		if (isZero) return "PT0S"

		return buildString(capacity = 24) {
			val hours = seconds / Seconds.perHour
			val minutes = seconds % Seconds.perHour / Seconds.perMinute
			val seconds = (seconds % Seconds.perMinute).toLong()
			val nanoseconds = partialNanoseconds.toLong()

			append("PT")
			if (hours != 0L) {
				append(hours)
				append('H')
			}
			if (minutes != 0L) {
				append(minutes)
				append('M')
			}
			if (seconds != 0L || nanoseconds != 0L || length <= 2) {
				if (seconds != 0L)
					append(seconds)
				else {
					if (nanoseconds < 0)
						append('-')

					append('0')
				}

				if (nanoseconds != 0L) {
					append('.')

					val nanosecondsString = nanoseconds.absoluteValue.toString()
					for (length in nanosecondsString.length until 9)
						append('0')

					append(nanosecondsString.trimEnd { it == '0' })
				}

				append('S')
			}
		}
	}


	override operator fun unaryMinus() =
		if (isZero) this else unchecked(seconds = -seconds, partialNanoseconds = -partialNanoseconds)


	companion object {

		private val iso8601Regex = Regex(
			"([-+]?)P(?:([-+]?\\d+)D)?(T(?:([-+]?\\d+)H)?(?:([-+]?\\d+)M)?(?:([-+]?\\d+)(?:[.,](\\d{0,9}))?S)?)?",
			RegexOption.IGNORE_CASE
		)

		val zero = Duration(seconds = Seconds.zero, partialNanoseconds = Nanoseconds.zero)


		fun of(
			days: Int = 0,
			hours: Int = 0,
			minutes: Int = 0,
			seconds: Int = 0,
			milliseconds: Int = 0,
			microseconds: Int = 0,
			nanoseconds: Int = 0
		) =
			of(
				days = days.toLong(),
				hours = hours.toLong(),
				minutes = minutes.toLong(),
				seconds = seconds.toLong(),
				milliseconds = milliseconds.toLong(),
				microseconds = microseconds.toLong(),
				nanoseconds = nanoseconds.toLong()
			)


		fun of(
			days: Long = 0,
			hours: Long = 0,
			minutes: Long = 0,
			seconds: Long = 0,
			milliseconds: Long = 0,
			microseconds: Long = 0,
			nanoseconds: Long = 0
		) =
			of(
				days = Days(days),
				hours = Hours(hours),
				minutes = Minutes(minutes),
				seconds = Seconds(seconds),
				milliseconds = Milliseconds(milliseconds),
				microseconds = Microseconds(microseconds),
				nanoseconds = Nanoseconds(nanoseconds)
			)


		// FIXME broken of values are negative
		fun of(
			days: Days = Days.zero,
			hours: Hours = Hours.zero,
			minutes: Minutes = Minutes.zero,
			seconds: Seconds = Seconds.zero,
			milliseconds: Milliseconds = Milliseconds.zero,
			microseconds: Microseconds = Microseconds.zero,
			nanoseconds: Nanoseconds = Nanoseconds.zero
		): Duration {
			var totalSeconds = seconds + minutes.toSeconds() + hours.toSeconds() + days.toSeconds()
			var partialNanoseconds = nanoseconds

			if (!milliseconds.isZero) {
				totalSeconds += milliseconds.toSeconds()
				partialNanoseconds += (milliseconds % Milliseconds.perSecond).toNanoseconds()
			}
			if (!microseconds.isZero) {
				totalSeconds += microseconds.toSeconds()
				partialNanoseconds += (microseconds % Microseconds.perSecond).toNanoseconds()
			}
			if (!partialNanoseconds.isZero) {
				totalSeconds += partialNanoseconds.toSeconds()
				partialNanoseconds %= Nanoseconds.perSecond.toLong()
			}

			return unchecked(seconds = totalSeconds, partialNanoseconds = partialNanoseconds)
		}


		fun parse(text: CharSequence): Duration? {
			val result = iso8601Regex.matchEntire(text) ?: return null // no match
			if (result.groupValues[3] == "T") return null // empty time

			val daysText = result.groupValues[2]
			val hoursText = result.groupValues[4]
			val minutesText = result.groupValues[5]
			val secondsText = result.groupValues[6]
			if (daysText.isEmpty() && hoursText.isEmpty() && minutesText.isEmpty() && secondsText.isEmpty()) return null // empty time

			val secondFractionString = result.groupValues[7]

			val multiplier = if (result.groupValues[1] == "-") -1 else 1
			val days = parseNumber(daysText, multiplier = multiplier)
			val hours = parseNumber(hoursText, multiplier = multiplier)
			val minutes = parseNumber(minutesText, multiplier = multiplier)
			val seconds = parseNumber(secondsText, multiplier = multiplier)
			val nanoseconds = parseFraction(secondFractionString) * if (seconds < 0) -1 else 1

			// FIXME throws
			return of(days = days, hours = hours, minutes = minutes, seconds = seconds, nanoseconds = nanoseconds.toLong())
		}


		internal fun unchecked(seconds: Long, partialNanoseconds: Long = 0) =
			unchecked(Seconds(seconds), partialNanoseconds = Nanoseconds(partialNanoseconds))


		internal fun unchecked(seconds: Seconds, partialNanoseconds: Nanoseconds = Nanoseconds.zero): Duration {
			if ((seconds.toLong() or partialNanoseconds.toLong()) == 0L) return zero

			return Duration(seconds, partialNanoseconds = partialNanoseconds)
		}
	}
}


private fun parseFraction(text: String): Int {
	if (text.isEmpty()) return 0

	var fraction = text.toInt()
	for (i in text.length until 9)
		fraction *= 10

	return fraction
}


private fun parseNumber(text: String, multiplier: Int): Long {
	if (text.isEmpty()) return 0

	return text.toLong() * multiplier
}


operator fun Int.times(other: Duration) =
	other.times(this)


operator fun Long.times(other: Duration) =
	other.times(this)


@Serializer(forClass = Duration::class)
internal object DurationSerializer : KSerializer<Duration> {

	override val descriptor = StringDescriptor.withName("com.github.fluidsonic.fluid.time.Duration")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			Duration.parse(string) ?: throw SerializationException("Invalid ISO 8601 duration format: $string")
		}


	override fun serialize(encoder: Encoder, obj: Duration) {
		encoder.encodeString(obj.toString())
	}
}
