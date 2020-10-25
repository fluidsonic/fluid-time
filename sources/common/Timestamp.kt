package io.fluidsonic.time

import kotlinx.serialization.*
import kotlinx.serialization.encoding.*


@Serializable(with = TimestampSerializer::class)
public class Timestamp private constructor(
	public val secondsSince1970: Seconds,
	public val partialNanosecond: NanosecondOfSecond
) : Comparable<Timestamp> {

	init {
		freeze()
	}


	override fun compareTo(other: Timestamp): Int {
		var result = secondsSince1970.compareTo(other.secondsSince1970)
		if (result == 0)
			result = partialNanosecond.compareTo(other.partialNanosecond)

		return result
	}


	public fun durationSince(other: Timestamp): PreciseDuration =
		this - other


	public fun durationUntil(other: Timestamp): PreciseDuration =
		other.durationSince(this)


	override fun equals(other: Any?): Boolean =
		this === other || (
			other is Timestamp
				&& secondsSince1970 == other.secondsSince1970
				&& partialNanosecond == other.partialNanosecond
			)


	override fun hashCode(): Int =
		secondsSince1970.hashCode() xor partialNanosecond.hashCode()


	public fun microsecondsSince(other: Timestamp): Microseconds =
		durationSince(other).toMicroseconds() // TODO optimize


	public fun microsecondsUntil(other: Timestamp): Microseconds =
		durationUntil(other).toMicroseconds() // TODO optimize


	public fun millisecondsSince(other: Timestamp): Milliseconds =
		durationSince(other).toMilliseconds() // TODO optimize


	public fun millisecondsUntil(other: Timestamp): Milliseconds =
		durationUntil(other).toMilliseconds() // TODO optimize


	public val millisecondsSince1970: Milliseconds
		get() = secondsSince1970.toMilliseconds() + Nanoseconds(partialNanosecond.toLong()).toMilliseconds() // FIXME check


	public operator fun minus(other: Days): Timestamp =
		minus(other.toSeconds())


	public operator fun minus(other: PreciseDuration): Timestamp =
		minus(seconds = other.seconds, nanoseconds = other.partialNanoseconds)


	public operator fun minus(other: Hours): Timestamp =
		minus(other.toSeconds())


	public operator fun minus(other: Microseconds): Timestamp =
		minus(seconds = other.toSeconds(), nanoseconds = (other % Microseconds.perSecond).toNanoseconds())


	public operator fun minus(other: Milliseconds): Timestamp =
		minus(seconds = other.toSeconds(), nanoseconds = (other % Milliseconds.perSecond).toNanoseconds())


	public operator fun minus(other: Minutes): Timestamp =
		minus(other.toSeconds())


	public operator fun minus(other: Nanoseconds): Timestamp =
		minus(seconds = other.toSeconds(), nanoseconds = other % Nanoseconds.perSecond)


	public operator fun minus(other: Seconds): Timestamp =
		minus(seconds = other, nanoseconds = Nanoseconds.zero)


	private fun minus(seconds: Seconds, nanoseconds: Nanoseconds) =
		of(secondsSince1970 = secondsSince1970 - seconds, nanoseconds = Nanoseconds(partialNanosecond.toLong()) - nanoseconds)


	public operator fun minus(other: Timestamp): PreciseDuration =
		PreciseDuration.of(seconds = secondsSince1970 - other.secondsSince1970, nanoseconds = partialNanosecond - other.partialNanosecond)


	public fun nanosecondsSince(other: Timestamp): Nanoseconds =
		durationSince(other).toNanoseconds() // TODO optimize


	public fun nanosecondsUntil(other: Timestamp): Nanoseconds =
		durationUntil(other).toNanoseconds() // TODO optimize


	public operator fun plus(other: Days): Timestamp =
		plus(other.toSeconds())


	public operator fun plus(other: PreciseDuration): Timestamp =
		plus(seconds = other.seconds, nanoseconds = other.partialNanoseconds)


	public operator fun plus(other: Hours): Timestamp =
		plus(other.toSeconds())


	public operator fun plus(other: Microseconds): Timestamp =
		plus(seconds = other.toSeconds(), nanoseconds = (other % Microseconds.perSecond).toNanoseconds())


	public operator fun plus(other: Milliseconds): Timestamp =
		plus(seconds = other.toSeconds(), nanoseconds = (other % Milliseconds.perSecond).toNanoseconds())


	public operator fun plus(other: Minutes): Timestamp =
		plus(other.toSeconds())


	public operator fun plus(other: Nanoseconds): Timestamp =
		plus(seconds = other.toSeconds(), nanoseconds = other % Nanoseconds.perSecond)


	public operator fun plus(other: Seconds): Timestamp =
		plus(seconds = other, nanoseconds = Nanoseconds.zero)


	private fun plus(seconds: Seconds, nanoseconds: Nanoseconds) =
		of(secondsSince1970 = secondsSince1970 + seconds, nanoseconds = Nanoseconds(partialNanosecond.toLong()) + nanoseconds)


	public fun secondsSince(other: Timestamp): Seconds =
		durationSince(other).toSeconds() // TODO optimize


	public fun secondsUntil(other: Timestamp): Seconds =
		durationUntil(other).toSeconds() // TODO optimize


	override fun toString(): String =
		buildString(capacity = 30) { toString(this) }


	// TODO this is not correct in all cases
	public fun toString(builder: StringBuilder) {
		val localDateTime = toLocalDateTime(TimeZone.utc)
		localDateTime.toString(builder)

		if (localDateTime.time.second == SecondOfMinute.min && localDateTime.time.nanosecond == NanosecondOfSecond.min)
			builder.append(":00")

		builder.append('Z')
	}


	public companion object {

		public val distantFuture: Timestamp = unchecked(secondsSince1970 = Seconds(31_556_889_864_403_199L), partialNanosecond = Nanoseconds(999_999_999))
		public val distantPast: Timestamp = unchecked(secondsSince1970 = Seconds(-31_557_014_167_219_200L))
		public val firstIn1970: Timestamp = unchecked(secondsSince1970 = Seconds.zero)


		public fun now(clock: WallClock = WallClock.systemUtc): Timestamp =
			clock.timestamp()


		public fun of(millisecondsSince1970: Milliseconds, nanoseconds: Nanoseconds = Nanoseconds.zero): Timestamp =
			of(secondsSince1970 = millisecondsSince1970.toSeconds(), nanoseconds = nanoseconds + (millisecondsSince1970 % Milliseconds.perSecond).toNanoseconds())


		// FIXME handle negative nanoseconds
		// FIXME rename to not have 1970 info as parameter name
		public fun of(secondsSince1970: Seconds, nanoseconds: Nanoseconds = Nanoseconds.zero): Timestamp {
			var totalSecondsSince1970 = secondsSince1970
			var partialNanoseconds = nanoseconds

			if (!partialNanoseconds.isZero) {
				totalSecondsSince1970 += partialNanoseconds.toSeconds()
				partialNanoseconds %= Nanoseconds.perSecond.toLong()
			}

			return unchecked(secondsSince1970 = totalSecondsSince1970, partialNanosecond = partialNanoseconds)
		}


		// TODO this is not correct in all cases
		public fun parse(text: CharSequence): Timestamp? {
			val suffixLength = when {
				text.endsWith('Z') -> 1
				text.endsWith("+00:00") -> 6
				text.endsWith("-00:00") -> 6
				else -> return null
			}

			val splitIndex = text.indexOf('T')
			if (splitIndex < 0 || splitIndex >= text.length - suffixLength - 1) return null

			val date = LocalDate.parse(text.substring(startIndex = 0, endIndex = splitIndex)) ?: return null
			val time = LocalTime.parse(text.substring(startIndex = splitIndex + 1, endIndex = text.length - suffixLength)) ?: return null

			return LocalDateTime.of(date, time).atTimeZone(TimeZone.utc)
		}


		internal fun unchecked(secondsSince1970: Long, partialNanosecond: Long = 0) =
			unchecked(secondsSince1970 = Seconds(secondsSince1970), partialNanosecond = Nanoseconds(partialNanosecond))


		internal fun unchecked(secondsSince1970: Seconds, partialNanosecond: Nanoseconds = Nanoseconds.zero) =
			Timestamp(secondsSince1970 = secondsSince1970, partialNanosecond = NanosecondOfSecond.unchecked(partialNanosecond.toLong()))
	}
}


public expect fun Timestamp.toDayOfWeek(timeZone: TimeZone): DayOfWeek
public expect fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate
public expect fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime
public expect fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime


@Serializer(forClass = Timestamp::class)
internal object TimestampSerializer : KSerializer<Timestamp> {

	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			Timestamp.parse(string) ?: throw SerializationException("Invalid ISO 8601 timestamp format: $string")
		}


	override fun serialize(encoder: Encoder, value: Timestamp) {
		encoder.encodeString(value.toString())
	}
}
