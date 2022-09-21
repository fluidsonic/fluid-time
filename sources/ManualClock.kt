package io.fluidsonic.time

import kotlin.js.*
import kotlin.jvm.*
import kotlin.time.*
import kotlinx.datetime.*


public interface ManualClock : Clock {

	public fun nowOrNull(): Timestamp?
	public fun set(timestamp: Timestamp?)


	override fun now(): Timestamp =
		nowOrNull() ?: error("ManualClock was used without specifying a timestamp.")


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


@JsName("manualClock")
public fun ManualClock(): ManualClock = DefaultManualClock()


public fun ManualClock.advance(duration: Duration): Timestamp =
	now()
		.plus(duration)
		.also(::set)


public fun ManualClock.advance(
	hours: Int = 0,
	minutes: Int = 0,
	seconds: Int = 0,
	nanoseconds: Int = 0,
): Timestamp =
	advance(Duration(hours = hours, minutes = minutes, seconds = seconds, nanoseconds = nanoseconds))


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


public fun ManualClock.advance(period: DateTimePeriod, timeZone: TimeZone): Timestamp =
	now()
		.plus(period, timeZone = timeZone)
		.also(::set)


public fun ManualClock.set(date: LocalDate, timeZone: TimeZone): Timestamp =
	set(date.atTime(LocalTime.midnight), timeZone = timeZone)


@JvmName("setOrNull")
public fun ManualClock.set(date: LocalDate?, timeZone: TimeZone): Timestamp? =
	set(date?.atTime(LocalTime.midnight), timeZone = timeZone)


public fun ManualClock.set(dateTime: LocalDateTime, timeZone: TimeZone): Timestamp =
	dateTime.toTimestamp(timeZone).also(::set)


@JvmName("setOrNull")
public fun ManualClock.set(dateTime: LocalDateTime?, timeZone: TimeZone): Timestamp? =
	dateTime?.toTimestamp(timeZone).also(::set)


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
