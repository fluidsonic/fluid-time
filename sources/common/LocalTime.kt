package io.fluidsonic.time

import kotlinx.serialization.*


@Serializable(with = LocalTimeSerializer::class)
class LocalTime private constructor(
	val hour: HourOfDay,
	val minute: MinuteOfHour,
	val second: SecondOfMinute,
	val nanosecond: NanosecondOfSecond
) : Comparable<LocalTime> {

	init {
		freeze()
	}


	fun atDate(year: Long, month: Long, day: Long) =
		atDate(Year.of(year), MonthOfYear.of(month), DayOfMonth.of(day))


	fun atDate(year: Year, month: MonthOfYear, day: DayOfMonth) =
		atDate(LocalDate.of(year, month, day))


	fun atDate(date: LocalDate) =
		LocalDateTime.of(date, this)


	override fun compareTo(other: LocalTime): Int {
		var result = hour.compareTo(other.hour)
		if (result == 0) {
			result = minute.compareTo(other.minute)
			if (result == 0) {
				result = second.compareTo(other.second)
				if (result == 0)
					result = nanosecond.compareTo(other.nanosecond)
			}
		}

		return result
	}


	override fun equals(other: Any?) =
		this === other || (
			other is LocalTime
				&& hour == other.hour
				&& minute == other.minute
				&& second == other.second
				&& nanosecond == other.nanosecond
			)


	override fun hashCode() =
		hour.hashCode() xor minute.hashCode() xor second.hashCode() xor nanosecond.hashCode()


	override fun toString() =
		buildString(capacity = 18) { toString(this) }


	fun toString(builder: StringBuilder) {
		with(builder) {
			val hour = hour.toLong()
			val minute = minute.toLong()
			val second = second.toLong()
			val nanosecond = nanosecond.toInt()

			append(if (hour < 10) "0" else "")
			append(hour)

			append(if (minute < 10) ":0" else ":")
			append(minute)

			if (second > 0 || nanosecond > 0) {
				append(if (second < 10) ":0" else ":")
				append(second)

				if (nanosecond > 0) {
					append('.')

					val fraction: Int
					val fractionLength: Int

					when {
						nanosecond % 1_000_000 == 0 -> {
							fraction = nanosecond / 1_000_000
							fractionLength = 3
						}
						nanosecond % 1_000 == 0 -> {
							fraction = nanosecond / 1_000
							fractionLength = 6
						}
						else -> {
							fraction = nanosecond
							fractionLength = 9
						}
					}

					val fractionString = fraction.toString()
					for (length in fractionString.length until fractionLength)
						append('0')
					append(fractionString)
				}
			}
		}
	}


	companion object {

		private val iso8601Regex = Regex("(\\d{2}):(\\d{2})(?::(\\d{2})(?:\\.(\\d{1,9}))?)?")

		private val fullHours = Array(24) { unchecked(hour = it.toLong()) }

		val max = unchecked(hour = 23, minute = 59, second = 59, nanosecond = 999_999_999)
		val midnight = fullHours[0]
		val min = midnight
		val noon = fullHours[12]


		fun now(clock: WallClock = WallClock.systemUtc) =
			clock.localTime()


		fun now(timeZone: TimeZone) =
			LocalDate.now(clock = WallClock.system(timeZone))


		fun of(hour: Long, minute: Long = 0, second: Long = 0, nanosecond: Long = 0) =
			of(HourOfDay.of(hour), MinuteOfHour.of(minute), SecondOfMinute.of(second), NanosecondOfSecond.of(nanosecond))


		fun of(
			hour: HourOfDay,
			minute: MinuteOfHour = MinuteOfHour(0),
			second: SecondOfMinute = SecondOfMinute(0),
			nanosecond: NanosecondOfSecond = NanosecondOfSecond(0)
		): LocalTime {
			if ((minute.toLong() or second.toLong() or nanosecond.toLong()) == 0L) return fullHours[hour.toInt()]

			return unchecked(hour, minute, second, nanosecond)
		}


		fun parse(text: CharSequence): LocalTime? {
			val result = iso8601Regex.matchEntire(text) ?: return null

			val hour = result.groupValues[1].toLong()
			val minute = result.groupValues[2].toLong()
			val second = result.groupValues[3].ifEmpty { null }?.toLong() ?: 0
			val nanosecond = parseFraction(result.groupValues[4])

			// FIXME throws but should return null
			return of(hour = hour, minute = minute, second = second, nanosecond = nanosecond)
		}


		internal fun unchecked(hour: Long, minute: Long = 0, second: Long = 0, nanosecond: Long = 0) =
			unchecked(HourOfDay.unchecked(hour), MinuteOfHour.unchecked(minute), SecondOfMinute.unchecked(second), NanosecondOfSecond.unchecked(nanosecond))


		internal fun unchecked(
			hour: HourOfDay,
			minute: MinuteOfHour = MinuteOfHour(0),
			second: SecondOfMinute = SecondOfMinute(0),
			nanosecond: NanosecondOfSecond = NanosecondOfSecond(0)
		) =
			LocalTime(hour, minute, second, nanosecond)
	}
}


private fun parseFraction(text: String): Long {
	if (text.isEmpty()) return 0

	var fraction = text.toLong()
	for (i in text.length until 9)
		fraction *= 10

	return fraction
}


@Serializer(forClass = LocalTime::class)
internal object LocalTimeSerializer : KSerializer<LocalTime> {

	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			LocalTime.parse(string) ?: throw SerializationException("Invalid ISO 8601 time format: $string")
		}


	override fun serialize(encoder: Encoder, value: LocalTime) {
		encoder.encodeString(value.toString())
	}
}
