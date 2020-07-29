@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlin.time.*
import kotlin.time.Duration


// TODO handle overflows
@Serializable(with = PreciseDurationSerializer::class)
public class PreciseDuration private constructor(
	public val seconds: Seconds,
	public val partialNanoseconds: Nanoseconds
) : TimeMeasurement<PreciseDuration> {

	init {
		// TODO check that both parameters have same sign

		freeze()
	}


	override val absolute: PreciseDuration
		get() = if (isNegative) -this else this


	override fun compareTo(other: PreciseDuration): Int {
		var result = seconds.compareTo(other.seconds)
		if (result == 0)
			result = partialNanoseconds.compareTo(other.partialNanoseconds)

		return result
	}


	@Deprecated(message = "not yet implemented", level = DeprecationLevel.HIDDEN)
	override operator fun div(other: Int): PreciseDuration =
		TODO("how to implement this?") // div(other.toLong())


	@Deprecated(message = "not yet implemented", level = DeprecationLevel.HIDDEN)
	override operator fun div(other: Long): PreciseDuration =
		when (other) {
			0L -> throw ArithmeticException("/ by zero")
			1L -> this
			-1L -> -this
			else -> TODO("how to implement this?")
		}


	@Deprecated(message = "not yet implemented", level = DeprecationLevel.HIDDEN)
	override fun div(other: PreciseDuration): Long =
		TODO("how to implement this?")


	override fun equals(other: Any?): Boolean =
		this === other || (
			other is PreciseDuration
				&& seconds == other.seconds
				&& partialNanoseconds == other.partialNanoseconds
			)


	override fun hashCode(): Int =
		seconds.hashCode() xor partialNanoseconds.hashCode()


	override val isNegative: Boolean
		get() = seconds.isNegative || partialNanoseconds.isNegative


	override val isPositive: Boolean
		get() = seconds.isPositive || partialNanoseconds.isPositive


	override val isZero: Boolean
		get() = (seconds.toLong() or partialNanoseconds.toLong()) == 0L


	override operator fun minus(other: PreciseDuration): PreciseDuration =
		when {
			isZero -> -other
			other.isZero -> this
			else -> plus(seconds = -other.seconds, nanoseconds = -other.partialNanoseconds)
		}


	public fun minus(
		days: Int = 0,
		hours: Int = 0,
		minutes: Int = 0,
		seconds: Int = 0,
		milliseconds: Int = 0,
		microseconds: Int = 0,
		nanoseconds: Int = 0
	): PreciseDuration =
		minus(
			days = days.toLong(),
			hours = hours.toLong(),
			minutes = minutes.toLong(),
			seconds = seconds.toLong(),
			milliseconds = milliseconds.toLong(),
			microseconds = microseconds.toLong(),
			nanoseconds = nanoseconds.toLong()
		)


	public fun minus(
		days: Long = 0,
		hours: Long = 0,
		minutes: Long = 0,
		seconds: Long = 0,
		milliseconds: Long = 0,
		microseconds: Long = 0,
		nanoseconds: Long = 0
	): PreciseDuration =
		minus(
			days = Days(days),
			hours = Hours(hours),
			minutes = Minutes(minutes),
			seconds = Seconds(seconds),
			milliseconds = Milliseconds(milliseconds),
			microseconds = Microseconds(microseconds),
			nanoseconds = Nanoseconds(nanoseconds)
		)


	public fun minus(
		days: Days = Days.zero,
		hours: Hours = Hours.zero,
		minutes: Minutes = Minutes.zero,
		seconds: Seconds = Seconds.zero,
		milliseconds: Milliseconds = Milliseconds.zero,
		microseconds: Microseconds = Microseconds.zero,
		nanoseconds: Nanoseconds = Nanoseconds.zero
	): PreciseDuration =
		plus(
			days = -days,
			hours = -hours,
			minutes = -minutes,
			seconds = -seconds,
			milliseconds = -milliseconds,
			microseconds = -microseconds,
			nanoseconds = -nanoseconds
		)


	override operator fun plus(other: PreciseDuration): PreciseDuration =
		when {
			isZero -> other
			other.isZero -> this
			else -> plus(seconds = other.seconds, nanoseconds = other.partialNanoseconds)
		}


	public fun plus(
		days: Int = 0,
		hours: Int = 0,
		minutes: Int = 0,
		seconds: Int = 0,
		milliseconds: Int = 0,
		microseconds: Int = 0,
		nanoseconds: Int = 0
	): PreciseDuration =
		plus(
			days = days.toLong(),
			hours = hours.toLong(),
			minutes = minutes.toLong(),
			seconds = seconds.toLong(),
			milliseconds = milliseconds.toLong(),
			microseconds = microseconds.toLong(),
			nanoseconds = nanoseconds.toLong()
		)


	public fun plus(
		days: Long = 0,
		hours: Long = 0,
		minutes: Long = 0,
		seconds: Long = 0,
		milliseconds: Long = 0,
		microseconds: Long = 0,
		nanoseconds: Long = 0
	): PreciseDuration =
		plus(
			days = Days(days),
			hours = Hours(hours),
			minutes = Minutes(minutes),
			seconds = Seconds(seconds),
			milliseconds = Milliseconds(milliseconds),
			microseconds = Microseconds(microseconds),
			nanoseconds = Nanoseconds(nanoseconds)
		)


	public fun plus(
		days: Days = Days.zero,
		hours: Hours = Hours.zero,
		minutes: Minutes = Minutes.zero,
		seconds: Seconds = Seconds.zero,
		milliseconds: Milliseconds = Milliseconds.zero,
		microseconds: Microseconds = Microseconds.zero,
		nanoseconds: Nanoseconds = Nanoseconds.zero
	): PreciseDuration {
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


	@Suppress("DEPRECATION")
	@Deprecated(message = "not yet implemented", level = DeprecationLevel.HIDDEN)
	override operator fun rem(other: Int): Nothing =
		TODO("how to implement this?")  // rem(other.toLong())


	@Deprecated(message = "not yet implemented", level = DeprecationLevel.HIDDEN)
	override operator fun rem(other: Long): PreciseDuration =
		when (other) {
			0L -> throw ArithmeticException("/ by zero")
			1L, -1L -> zero
			else -> TODO("how to implement this?")
		}


	@Deprecated(message = "not yet implemented", level = DeprecationLevel.HIDDEN)
	override fun rem(other: PreciseDuration): PreciseDuration =
		TODO("how to implement this?")


	override operator fun times(other: Int): PreciseDuration =
		times(other.toLong())


	override operator fun times(other: Long): PreciseDuration =
		when (other) {
			0L -> zero
			1L -> this
			-1L -> unchecked(seconds = -seconds, partialNanoseconds = -partialNanoseconds)
			else -> of(seconds = seconds * other, nanoseconds = partialNanoseconds * other)
		}


	override inline fun toDays(): Days =
		seconds.toDays()


	// TODO add overflow handling
	@ExperimentalTime
	override inline fun toDuration(): Duration =
		when {
			partialNanoseconds.isZero -> seconds.toDuration()
			else -> (seconds.toNanoseconds() + partialNanoseconds).toDuration()
		}


	override inline fun toHours(): Hours =
		seconds.toHours()


	override inline fun toMicroseconds(): Microseconds =
		seconds.toMicroseconds() + partialNanoseconds.toMicroseconds()


	override inline fun toMilliseconds(): Milliseconds =
		seconds.toMilliseconds() + partialNanoseconds.toMilliseconds()


	override inline fun toMinutes(): Minutes =
		seconds.toMinutes()


	override inline fun toNanoseconds(): Nanoseconds =
		seconds.toNanoseconds() + partialNanoseconds


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toPreciseDuration(): PreciseDuration =
		this


	override inline fun toSeconds(): Seconds =
		seconds


	override fun toString(): String {
		if (isZero) return "PT0S"

		return buildString(capacity = 24) {
			val totalSeconds = seconds.absolute
			val hours = totalSeconds / Seconds.perHour
			val minutes = totalSeconds % Seconds.perHour / Seconds.perMinute
			val seconds = (totalSeconds % Seconds.perMinute).toLong()
			val nanoseconds = partialNanoseconds.absolute.toLong()

			if (isNegative)
				append('-')

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

					val nanosecondsString = nanoseconds.toString()
					for (length in nanosecondsString.length until 9)
						append('0')

					append(nanosecondsString.trimEnd { it == '0' })
				}

				append('S')
			}
		}
	}


	override operator fun unaryMinus(): PreciseDuration =
		if (isZero) this else unchecked(seconds = -seconds, partialNanoseconds = -partialNanoseconds)


	public companion object {

		private val iso8601Regex = Regex(
			"([-+]?)P(?:([-+]?\\d+)D)?(T(?:([-+]?\\d+)H)?(?:([-+]?\\d+)M)?(?:([-+]?\\d+)(?:[.,](\\d{0,9}))?S)?)?",
			RegexOption.IGNORE_CASE
		)

		// TODO add min/max
		public val zero: PreciseDuration = PreciseDuration(seconds = Seconds.zero, partialNanoseconds = Nanoseconds.zero)


		public fun of(
			days: Int = 0,
			hours: Int = 0,
			minutes: Int = 0,
			seconds: Int = 0,
			milliseconds: Int = 0,
			microseconds: Int = 0,
			nanoseconds: Int = 0
		): PreciseDuration =
			of(
				days = days.toLong(),
				hours = hours.toLong(),
				minutes = minutes.toLong(),
				seconds = seconds.toLong(),
				milliseconds = milliseconds.toLong(),
				microseconds = microseconds.toLong(),
				nanoseconds = nanoseconds.toLong()
			)


		public fun of(
			days: Long = 0,
			hours: Long = 0,
			minutes: Long = 0,
			seconds: Long = 0,
			milliseconds: Long = 0,
			microseconds: Long = 0,
			nanoseconds: Long = 0
		): PreciseDuration =
			of(
				days = Days(days),
				hours = Hours(hours),
				minutes = Minutes(minutes),
				seconds = Seconds(seconds),
				milliseconds = Milliseconds(milliseconds),
				microseconds = Microseconds(microseconds),
				nanoseconds = Nanoseconds(nanoseconds)
			)


		public fun of(
			days: Days = Days.zero,
			hours: Hours = Hours.zero,
			minutes: Minutes = Minutes.zero,
			seconds: Seconds = Seconds.zero,
			milliseconds: Milliseconds = Milliseconds.zero,
			microseconds: Microseconds = Microseconds.zero,
			nanoseconds: Nanoseconds = Nanoseconds.zero
		): PreciseDuration {
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

			when {
				totalSeconds.isNegative ->
					if (partialNanoseconds.isPositive) {
						totalSeconds += Seconds(1)
						partialNanoseconds = Nanoseconds(1_000_000_000) - partialNanoseconds
					}

				totalSeconds.isPositive ->
					if (partialNanoseconds.isNegative) {
						totalSeconds -= Seconds(1)
						partialNanoseconds = Nanoseconds(1_000_000_000) + partialNanoseconds
					}
			}

			return unchecked(seconds = totalSeconds, partialNanoseconds = partialNanoseconds)
		}


		public fun parse(text: CharSequence): PreciseDuration? {
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

			// FIXME throws but should return null
			return of(days = days, hours = hours, minutes = minutes, seconds = seconds, nanoseconds = nanoseconds.toLong())
		}


		internal fun unchecked(seconds: Long, partialNanoseconds: Long = 0) =
			unchecked(Seconds(seconds), partialNanoseconds = Nanoseconds(partialNanoseconds))


		internal fun unchecked(seconds: Seconds, partialNanoseconds: Nanoseconds = Nanoseconds.zero): PreciseDuration {
			if ((seconds.toLong() or partialNanoseconds.toLong()) == 0L) return zero

			return PreciseDuration(seconds, partialNanoseconds = partialNanoseconds)
		}
	}
}


@ExperimentalTime
public inline fun Duration.toPreciseDuration(): PreciseDuration =
	inSeconds.let { seconds ->
		PreciseDuration.of(
			seconds = seconds.toLong(),
			nanoseconds = ((seconds % 1) * 1_000_000_000L).toLong() % 1_000_000_000L
		)
	}


public operator fun Int.times(other: PreciseDuration): PreciseDuration =
	other.times(this)


public operator fun Long.times(other: PreciseDuration): PreciseDuration =
	other.times(this)


@Serializer(forClass = PreciseDuration::class)
internal object PreciseDurationSerializer : KSerializer<PreciseDuration> {

	override val descriptor = PrimitiveSerialDescriptor("io.fluidsonic.time.PreciseDuration", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			PreciseDuration.parse(string) ?: throw SerializationException("Invalid ISO 8601 duration format: $string")
		}


	override fun serialize(encoder: Encoder, value: PreciseDuration) {
		encoder.encodeString(value.toString())
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
