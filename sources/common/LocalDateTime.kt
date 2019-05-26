package com.github.fluidsonic.fluid.time

import kotlinx.serialization.*


@Serializable(with = LocalDateTimeSerializer::class)
class LocalDateTime private constructor(
	val date: LocalDate,
	val time: LocalTime
) : Comparable<LocalDateTime> {

	override fun compareTo(other: LocalDateTime): Int {
		var result = date.compareTo(other.date)
		if (result == 0)
			result = time.compareTo(other.time)

		return result
	}


	override fun equals(other: Any?) =
		this === other || (
			other is LocalDateTime
				&& date == other.date
				&& time == other.time
			)


	override fun hashCode() =
		date.hashCode() xor time.hashCode()


	override fun toString() =
		buildString(capacity = 29) { toString(this) }


	fun toString(builder: StringBuilder) {
		date.toString(builder)
		builder.append('T')
		time.toString(builder)
	}


	companion object {

		val firstIn1970 = LocalDate.firstIn1970.atTime(LocalTime.min)


		fun of(year: Long, month: Long, day: Long, hour: Long, minute: Long = 0, second: Long = 0, nanosecond: Long = 0) =
			of(Year.of(year), MonthOfYear.of(month), DayOfMonth.of(day), HourOfDay.of(hour), MinuteOfHour.of(minute), SecondOfMinute.of(second), NanosecondOfSecond.of(nanosecond))


		fun of(year: Year, month: MonthOfYear, day: DayOfMonth, hour: HourOfDay, minute: MinuteOfHour = MinuteOfHour.zero, second: SecondOfMinute = SecondOfMinute.zero, nanosecond: NanosecondOfSecond = NanosecondOfSecond.zero) =
			of(LocalDate.of(year, month, day), LocalTime.of(hour, minute, second, nanosecond))


		fun of(date: LocalDate, time: LocalTime) =
			unchecked(date, time)


		fun parse(text: CharSequence): LocalDateTime? {
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


		internal fun unchecked(year: Year, month: MonthOfYear, day: DayOfMonth, hour: HourOfDay, minute: MinuteOfHour = MinuteOfHour.zero, second: SecondOfMinute = SecondOfMinute.zero, nanosecond: NanosecondOfSecond = NanosecondOfSecond.zero) =
			LocalDateTime(LocalDate.unchecked(year, month, day), LocalTime.unchecked(hour, minute, second, nanosecond))


		internal fun unchecked(date: LocalDate, time: LocalTime) =
			LocalDateTime(date, time)
	}
}


expect val LocalDateTime.dayOfWeek: DayOfWeek

expect fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp


@Serializer(forClass = LocalDateTime::class)
internal object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			LocalDateTime.parse(string) ?: throw SerializationException("Invalid ISO 8601 date/time format: $string")
		}


	override fun serialize(encoder: Encoder, obj: LocalDateTime) {
		encoder.encodeString(obj.toString())
	}
}
