package com.github.fluidsonic.fluid.time

import kotlinx.serialization.*
import kotlin.math.*


@Serializable(with = LocalDateSerializer::class)
class LocalDate private constructor(
	val year: Year,
	val month: MonthOfYear,
	val day: DayOfMonth
) : Comparable<LocalDate> {

	init {
		freeze()
	}


	fun atTime(hour: Long, minute: Long = 0, second: Long = 0, nanosecond: Long = 0) =
		atTime(HourOfDay.of(hour), MinuteOfHour.of(minute), SecondOfMinute.of(second), NanosecondOfSecond.of(nanosecond))


	fun atTime(
		hour: HourOfDay,
		minute: MinuteOfHour = MinuteOfHour(0),
		second: SecondOfMinute = SecondOfMinute(0),
		nanosecond: NanosecondOfSecond = NanosecondOfSecond(0)
	) =
		atTime(LocalTime.of(hour, minute, second, nanosecond))


	fun atTime(time: LocalTime) =
		LocalDateTime.of(this, time)


	override fun compareTo(other: LocalDate): Int {
		var result = year.compareTo(other.year)
		if (result == 0) {
			result = month.compareTo(other.month)
			if (result == 0)
				result = day.compareTo(other.day)
		}

		return result
	}


	override fun equals(other: Any?) =
		this === other || (
			other is LocalDate
				&& day == other.day
				&& month == other.month
				&& year == other.year
			)


	override fun hashCode() =
		day.hashCode() xor month.hashCode() xor year.hashCode()


	override fun toString() =
		buildString(capacity = 10) { toString(this) }


	fun toString(builder: StringBuilder) {
		with(builder) {
			when {
				year.toLong() < 0 -> append('-')
				year.toLong() > 9999 -> append('+')
			}

			val year = year.toLong().absoluteValue
			val month = month.toLong()
			val day = day.toLong()

			if (year < 1000) {
				append('0')
				if (year < 100) {
					append('0')
					if (year < 10) {
						append('0')
					}
				}
			}
			append(year)

			append(if (month < 10) "-0" else "-")
			append(month)
			append(if (day < 10) "-0" else "-")
			append(day)
		}
	}


	companion object {

		private val iso8601Regex = Regex("([+-]?)(\\d{4,10})-(\\d{2})-(\\d{2})")

		val firstIn1970 = unchecked(year = 1970, month = 1, day = 1)


		fun of(year: Long, month: Long, day: Long) =
			of(Year.of(year), MonthOfYear.of(month), DayOfMonth.of(day))


		fun of(year: Year, month: MonthOfYear, day: DayOfMonth): LocalDate {
			if (day > month.lastDayIn(year))
				throw IllegalArgumentException("'$day' is not a valid day in '${month.name} $year'")

			return unchecked(year, month, day)
		}


		fun parse(text: CharSequence): LocalDate? {
			val result = iso8601Regex.matchEntire(text) ?: return null

			val sign = result.groupValues[1]
			var year = result.groupValues[2].toLong()
			val month = result.groupValues[3].toLong()
			val day = result.groupValues[4].toLong()

			when (sign) {
				"+" -> if (year < 10_000) return null
				"-" -> year = -year
				else -> if (year >= 10_000) return null
			}

			// FIXME throws but should return null
			return of(year = year, month = month, day = day)
		}


		internal fun unchecked(year: Long, month: Long, day: Long) =
			unchecked(Year.unchecked(year), MonthOfYear.unchecked(month), DayOfMonth.unchecked(day))


		internal fun unchecked(year: Year, month: MonthOfYear, day: DayOfMonth) =
			LocalDate(year, month, day)
	}
}


expect fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp
expect fun LocalDate.minusDays(daysToSubtract: Long): LocalDate
expect fun LocalDate.plusDays(daysToAdd: Long): LocalDate
expect fun LocalDate.toDayOfWeek(): DayOfWeek


fun LocalDate.atStartOfDay() =
	atTime(LocalTime.midnight)


@Serializer(forClass = LocalDate::class)
internal object LocalDateSerializer : KSerializer<LocalDate> {

	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			LocalDate.parse(string) ?: throw SerializationException("Invalid ISO 8601 date format: $string")
		}


	override fun serialize(encoder: Encoder, obj: LocalDate) {
		encoder.encodeString(obj.toString())
	}
}
