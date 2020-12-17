package io.fluidsonic.time

import kotlinx.datetime.*


public expect class LocalTime
/**
 * Constructs a [LocalTime] instance from the given time components.
 *
 * The supported ranges of components:
 * - [hour] `0..23`
 * - [minute] `0..59`
 * - [second] `0..59`
 * - [nanosecond] `0..999_999_999`
 *
 * @throws IllegalArgumentException if any parameter is out of range.
 */
	(hour: Int, minute: Int, second: Int = 0, nanosecond: Int = 0) : Comparable<LocalTime> {

	/** Returns the hour-of-day time component of this date/time value. */
	public val hour: Int

	/** Returns the minute-of-hour time component of this date/time value. */
	public val minute: Int

	/** Returns the second-of-minute time component of this date/time value. */
	public val second: Int

	/** Returns the nanosecond-of-second time component of this date/time value. */
	public val nanosecond: Int

	/**
	 * Compares `this` time value with the [other] time value.
	 * Returns zero if this value is equal to the other,
	 * a negative number if this value represents earlier civil time than the other,
	 * and a positive number if this value represents later civil time than the other.
	 */
	public override operator fun compareTo(other: LocalTime): Int

	/**
	 * Converts this time value to the ISO-8601 string representation.
	 *
	 * @see LocalTime.parse
	 */
	public override fun toString(): String


	public companion object {

		/**
		 * Parses a string that represents a time value in ISO-8601 format and returns the parsed [LocalTime] value.
		 *
		 * Examples of date/time in ISO-8601 format:
		 * - `18:43`
		 * - `18:43:00`
		 * - `18:43:00.500`
		 * - `18:43:00.123456789`
		 *
		 * @throws IllegalArgumentException if the text cannot be parsed or the boundaries of [LocalTime] are exceeded.
		 */
		public fun parse(isoString: String): LocalTime

		public val min: LocalTime
		public val max: LocalTime
	}
}


public fun LocalTime.atDate(date: LocalDate): LocalDateTime =
	LocalDateTime(
		year = date.year,
		monthNumber = date.monthNumber,
		dayOfMonth = date.dayOfMonth,
		hour = hour,
		minute = minute,
		second = second,
		nanosecond = nanosecond
	)


public val LocalTime.Companion.midnight: LocalTime
	get() = min


public fun LocalTime.Companion.parseOrNull(isoString: String): LocalTime? =
	runCatching { parse(isoString) }.getOrNull()


/**
 * Converts this string representing a time value in ISO-8601 format to a [LocalTime] value.
 *
 * See [LocalTime.parse] for examples of time string representations.
 *
 * @throws IllegalArgumentException if the text cannot be parsed or the boundaries of [LocalTime] are exceeded.
 */
public fun String.toLocalTime(): LocalTime =
	LocalTime.parse(this)
