package io.fluidsonic.time

import kotlin.concurrent.Volatile
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.time.Duration
import kotlin.time.Clock
import kotlinx.datetime.*


/** A [Clock] with a manually controlled timestamp, useful for testing. */
public interface ManualClock : Clock {

	/** Returns the current timestamp, or `null` if none has been set. */
	public fun nowOrNull(): Timestamp?

	/** Sets the current timestamp. Pass `null` to clear it. */
	public fun set(timestamp: Timestamp?)


	override fun now(): Timestamp =
		checkNotNull(nowOrNull()) { "ManualClock was used without specifying a timestamp." }


	public companion object
}


private class DefaultManualClock : ManualClock {

	@Volatile
	private var timestamp: Timestamp? = null


	override fun nowOrNull(): Timestamp? =
		timestamp


	override fun set(timestamp: Timestamp?) {
		this.timestamp = timestamp
	}


	override fun toString() =
		"ManualClock(timestamp = $timestamp)"
}


/** Creates a new [ManualClock] with no initial timestamp. */
@JsName("manualClock")
public fun ManualClock(): ManualClock = DefaultManualClock()


/** Advances the clock by the given [duration] and returns the new timestamp. */
public fun ManualClock.advance(duration: Duration): Timestamp =
	now()
		.plus(duration)
		.also(::set)


/** Advances the clock by the given time components and returns the new timestamp. */
public fun ManualClock.advance(
	hours: Int = 0,
	minutes: Int = 0,
	seconds: Int = 0,
	nanoseconds: Int = 0,
): Timestamp =
	advance(Duration(hours = hours, minutes = minutes, seconds = seconds, nanoseconds = nanoseconds))


/** Advances the clock by the given date and time components in the specified [timeZone] and returns the new timestamp. */
public fun ManualClock.advance(
	years: Int = 0,
	months: Int = 0,
	days: Int = 0,
	hours: Int = 0,
	minutes: Int = 0,
	seconds: Int = 0,
	nanoseconds: Int = 0,
	timeZone: TimeZone,
): Timestamp =
	advance(
		period = DateTimePeriod(
			years = years,
			months = months,
			days = days,
			hours = hours,
			minutes = minutes,
			seconds = seconds,
			nanoseconds = nanoseconds.toLong(),
		),
		timeZone = timeZone,
	)


/** Advances the clock by the given [period] in the specified [timeZone] and returns the new timestamp. */
public fun ManualClock.advance(period: DateTimePeriod, timeZone: TimeZone): Timestamp =
	now()
		.plus(period, timeZone = timeZone)
		.also(::set)


/** Sets the clock to midnight of the given [date] in the specified [timeZone]. */
public fun ManualClock.set(date: LocalDate, timeZone: TimeZone): Timestamp =
	set(date.atTime(LocalTime.midnight), timeZone = timeZone)


/** Sets the clock to midnight of the given [date] in the specified [timeZone], or clears it if [date] is `null`. */
@JvmName("setOrNull")
public fun ManualClock.set(date: LocalDate?, timeZone: TimeZone): Timestamp? =
	set(date?.atTime(LocalTime.midnight), timeZone = timeZone)


/** Sets the clock to the given [dateTime] in the specified [timeZone]. */
public fun ManualClock.set(dateTime: LocalDateTime, timeZone: TimeZone): Timestamp =
	dateTime.toTimestamp(timeZone).also(::set)


/** Sets the clock to the given [dateTime] in the specified [timeZone], or clears it if [dateTime] is `null`. */
@JvmName("setOrNull")
public fun ManualClock.set(dateTime: LocalDateTime?, timeZone: TimeZone): Timestamp? =
	dateTime?.toTimestamp(timeZone).also(::set)


/** Sets the clock to the given date and time components in the specified [timeZone]. */
public fun ManualClock.set(
	year: Int,
	month: Int,
	day: Int,
	hour: Int = 0,
	minute: Int = 0,
	second: Int = 0,
	nanosecond: Int = 0,
	timeZone: TimeZone,
): Timestamp =
	set(LocalDateTime(year, month, day, hour, minute, second, nanosecond), timeZone = timeZone)
