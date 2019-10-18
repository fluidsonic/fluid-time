@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Nanoseconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Nanoseconds>,
	TimeMeasurement<Nanoseconds> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(Long::absoluteValue)


	override inline fun compareTo(other: Nanoseconds) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Nanoseconds(value / other)


	override inline operator fun div(other: Nanoseconds) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Nanoseconds(transform(toLong()))


	override inline operator fun minus(other: Nanoseconds) =
		Nanoseconds(value - other.value)


	override inline operator fun plus(other: Nanoseconds) =
		Nanoseconds(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Nanoseconds(value % other)


	override inline operator fun rem(other: Nanoseconds) =
		Nanoseconds(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Nanoseconds(value * other)


	override inline fun toDays() =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration() =
		value.nanoseconds


	override inline fun toHours() =
		Hours(this / perHour)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toMicroseconds() =
		Microseconds(this / perMicrosecond)


	override inline fun toMilliseconds() =
		Milliseconds(this / perMillisecond)


	override inline fun toMinutes() =
		Minutes(this / perMinute)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toNanoseconds() =
		this


	override inline fun toPreciseDuration() =
		PreciseDuration.of(nanoseconds = this)


	override inline fun toSeconds() =
		Seconds(this / perSecond)


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Nanoseconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Nanoseconds> {

		/* override */ val max = Nanoseconds(Long.MAX_VALUE)
		/* override */ val min = Nanoseconds(Long.MIN_VALUE)
		val perMicrosecond = Nanoseconds(1_000L)
		val perMillisecond = Microseconds.perMillisecond.toNanoseconds()
		val perSecond = Milliseconds.perSecond.toNanoseconds()
		val perMinute = Seconds.perMinute.toNanoseconds()
		val perHour = Minutes.perHour.toNanoseconds()
		val perDay = Hours.perDay.toNanoseconds()
		/* override */ val zero = Nanoseconds(0L)
	}
}


@ExperimentalTime
inline fun Duration.toNanoseconds() =
	Nanoseconds(inNanoseconds.toLong())


inline operator fun Int.times(other: Nanoseconds) =
	other.times(this)


inline operator fun Long.times(other: Nanoseconds) =
	other.times(this)
