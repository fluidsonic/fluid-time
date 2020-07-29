package io.fluidsonic.time

import kotlinx.serialization.*
import kotlinx.serialization.encoding.*


@Serializable(with = LocalDateTimeSerializer::class)
public class LocalDateTime private constructor(
	public val date: LocalDate,
	public val time: LocalTime
) : Comparable<LocalDateTime> {

	init {
		freeze()
	}


	override fun compareTo(other: LocalDateTime): Int {
		var result = date.compareTo(other.date)
		if (result == 0)
			result = time.compareTo(other.time)

		return result
	}


	override fun equals(other: Any?): Boolean =
		this === other || (
			other is LocalDateTime
				&& date == other.date
				&& time == other.time
			)


	override fun hashCode(): Int =
		date.hashCode() xor time.hashCode()


	override fun toString(): String =
		buildString(capacity = 29) { toString(this) }


	public fun toString(builder: StringBuilder) {
		date.toString(builder)
		builder.append('T')
		time.toString(builder)
	}


	public companion object {

		public val firstIn1970: LocalDateTime = LocalDate.firstIn1970.atTime(LocalTime.min)


		public fun now(clock: WallClock = WallClock.systemUtc): LocalDateTime =
			clock.localDateTime()


		public fun now(timeZone: TimeZone): LocalDateTime =
			now(clock = WallClock.system(timeZone))


		public fun of(year: Long, month: Long, day: Long, hour: Long = 0, minute: Long = 0, second: Long = 0, nanosecond: Long = 0): LocalDateTime =
			of(Year.of(year), MonthOfYear.of(month), DayOfMonth.of(day), HourOfDay.of(hour), MinuteOfHour.of(minute), SecondOfMinute.of(second), NanosecondOfSecond.of(nanosecond))


		public fun of(
			year: Year,
			month: MonthOfYear,
			day: DayOfMonth,
			hour: HourOfDay,
			minute: MinuteOfHour = MinuteOfHour(0),
			second: SecondOfMinute = SecondOfMinute(0),
			nanosecond: NanosecondOfSecond = NanosecondOfSecond(0)
		): LocalDateTime =
			of(LocalDate.of(year, month, day), LocalTime.of(hour, minute, second, nanosecond))


		public fun of(date: LocalDate, time: LocalTime): LocalDateTime =
			unchecked(date, time)


		public fun parse(text: CharSequence): LocalDateTime? {
			val splitIndex = text.indexOf('T')
			if (splitIndex < 0 || splitIndex >= text.length - 1) return null

			val date = LocalDate.parse(text.substring(0, splitIndex)) ?: return null
			val time = LocalTime.parse(text.substring(splitIndex + 1)) ?: return null

			return of(date, time)
		}


		internal fun unchecked(year: Long, month: Long, day: Long, hour: Long, minute: Long = 0, second: Long = 0, nanosecond: Long = 0) =
			unchecked(
				Year.unchecked(year), MonthOfYear.unchecked(month), DayOfMonth.unchecked(day),
				HourOfDay.unchecked(hour), MinuteOfHour.unchecked(minute), SecondOfMinute.unchecked(second), NanosecondOfSecond.unchecked(nanosecond)
			)


		internal fun unchecked(
			year: Year,
			month: MonthOfYear,
			day: DayOfMonth,
			hour: HourOfDay,
			minute: MinuteOfHour = MinuteOfHour(0),
			second: SecondOfMinute = SecondOfMinute(0),
			nanosecond: NanosecondOfSecond = NanosecondOfSecond(0)
		) =
			LocalDateTime(LocalDate.unchecked(year, month, day), LocalTime.unchecked(hour, minute, second, nanosecond))


		internal fun unchecked(date: LocalDate, time: LocalTime) =
			LocalDateTime(date, time)
	}
}


public expect fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp
public expect fun LocalDateTime.toDayOfWeek(): DayOfWeek


@Serializer(forClass = LocalDateTime::class)
internal object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			LocalDateTime.parse(string) ?: throw SerializationException("Invalid ISO 8601 date/time format: $string")
		}


	override fun serialize(encoder: Encoder, value: LocalDateTime) {
		encoder.encodeString(value.toString())
	}
}
