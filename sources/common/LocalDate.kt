package io.fluidsonic.time

import kotlin.math.*
import kotlinx.serialization.*
import kotlinx.serialization.encoding.*


@Serializable(with = LocalDateSerializer::class)
public class LocalDate private constructor(
	public val year: Year,
	public val month: MonthOfYear,
	public val day: DayOfMonth,
) : Comparable<LocalDate> {

	init {
		freeze()
	}


	public fun atTime(hour: Long, minute: Long = 0, second: Long = 0, nanosecond: Long = 0): LocalDateTime =
		atTime(HourOfDay.of(hour), MinuteOfHour.of(minute), SecondOfMinute.of(second), NanosecondOfSecond.of(nanosecond))


	public fun atTime(
		hour: HourOfDay,
		minute: MinuteOfHour = MinuteOfHour(0),
		second: SecondOfMinute = SecondOfMinute(0),
		nanosecond: NanosecondOfSecond = NanosecondOfSecond(0),
	): LocalDateTime =
		atTime(LocalTime.of(hour, minute, second, nanosecond))


	public fun atTime(time: LocalTime): LocalDateTime =
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


	override fun equals(other: Any?): Boolean =
		this === other || (
			other is LocalDate
				&& day == other.day
				&& month == other.month
				&& year == other.year
			)


	override fun hashCode(): Int =
		day.hashCode() xor month.hashCode() xor year.hashCode()


	public fun periodSince(other: LocalDate): Period =
		Period.between(other, this)


	public fun periodUntil(other: LocalDate): Period =
		other.periodSince(this)


	override fun toString(): String =
		buildString(capacity = 10) { toString(this) }


	public fun toString(builder: StringBuilder) {
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


	public companion object {

		private val iso8601Regex = Regex("([+-]?)(\\d{4,10})-(\\d{2})-(\\d{2})")

		public val firstIn1970: LocalDate = unchecked(year = 1970, month = 1, day = 1)


		public fun now(clock: WallClock = WallClock.systemUtc): LocalDate =
			clock.localDate()


		public fun now(timeZone: TimeZone): LocalDate =
			now(clock = WallClock.system(timeZone))


		public fun of(year: Long, month: Long, day: Long): LocalDate =
			of(Year.of(year), MonthOfYear.of(month), DayOfMonth.of(day))


		public fun of(year: Year, month: MonthOfYear, day: DayOfMonth): LocalDate {
			require(day.isValidIn(month, year)) { "Day is not a valid day in '$month $year': $day" }

			return unchecked(year, month, day)
		}


		public fun ofOrNull(year: Long, month: Long, day: Long): LocalDate? {
			if (!Year.isValid(year) || !MonthOfYear.isValid(month) || !DayOfMonth.isValid(day))
				return null

			return ofOrNull(Year.of(year), MonthOfYear.of(month), DayOfMonth.of(day))
		}


		public fun ofOrNull(year: Year, month: MonthOfYear, day: DayOfMonth): LocalDate? {
			if (!day.isValidIn(month, year))
				return null

			return unchecked(year, month, day)
		}


		public fun parse(text: CharSequence): LocalDate? {
			val result = iso8601Regex.matchEntire(text) ?: return null

			val sign = result.groupValues[1]
			val year = result.groupValues[2].toLong().let { year ->
				when (sign) {
					"-" -> -year
					else -> year
				}
			}
			val month = result.groupValues[3].toLong()
			val day = result.groupValues[4].toLong()

			when (sign) {
				"+" -> if (year <= 9999) return null
				"-" -> Unit
				else -> if (year > 9999) return null
			}

			return ofOrNull(year = year, month = month, day = day)
		}


		internal fun unchecked(year: Long, month: Long, day: Long) =
			unchecked(Year.unchecked(year), MonthOfYear.unchecked(month), DayOfMonth.unchecked(day))


		internal fun unchecked(year: Year, month: MonthOfYear, day: DayOfMonth) =
			LocalDate(year, month, day)
	}
}


public expect fun LocalDate.atEndOfDay(timeZone: TimeZone): Timestamp
public expect fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp
public expect fun LocalDate.daysSince(startExclusive: LocalDate): Days
public expect fun LocalDate.daysUntil(endExclusive: LocalDate): Days
public expect fun LocalDate.toDayOfWeek(): DayOfWeek

public operator fun LocalDate.minus(days: Days): LocalDate =
	this + -days

public operator fun LocalDate.minus(months: Months): LocalDate =
	this + -months

public operator fun LocalDate.minus(years: Years): LocalDate =
	this + -years

public expect operator fun LocalDate.plus(days: Days): LocalDate
public expect operator fun LocalDate.plus(months: Months): LocalDate
public expect operator fun LocalDate.plus(years: Years): LocalDate


public fun LocalDate.atEndOfDay(): LocalDateTime =
	atTime(LocalTime.max)


public fun LocalDate.atStartOfDay(): LocalDateTime =
	atTime(LocalTime.min)


@Serializer(forClass = LocalDate::class)
internal object LocalDateSerializer : KSerializer<LocalDate> {

	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			LocalDate.parse(string) ?: throw SerializationException("Invalid ISO 8601 date format: $string")
		}


	override fun serialize(encoder: Encoder, value: LocalDate) {
		encoder.encodeString(value.toString())
	}
}
