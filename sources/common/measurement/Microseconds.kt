@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Microseconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Microseconds>,
	TimeMeasurement<Microseconds> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(Long::absoluteValue)


	override inline fun compareTo(other: Microseconds) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Microseconds(value / other)


	override inline operator fun div(other: Microseconds) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Microseconds(transform(toLong()))


	override inline operator fun minus(other: Microseconds) =
		Microseconds(value - other.value)


	override inline operator fun plus(other: Microseconds) =
		Microseconds(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Microseconds(value % other)


	override inline operator fun rem(other: Microseconds) =
		Microseconds(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Microseconds(value * other)


	override inline fun toDays() =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration() =
		value.microseconds


	override inline fun toHours() =
		Hours(this / perHour)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toMicroseconds() =
		this


	override inline fun toMilliseconds() =
		Milliseconds(this / perMillisecond)


	override inline fun toMinutes() =
		Minutes(this / perMinute)


	override inline fun toNanoseconds() =
		Nanoseconds.perMicrosecond * value


	override inline fun toPreciseDuration() =
		PreciseDuration.of(microseconds = this)


	override inline fun toSeconds() =
		Seconds(this / perSecond)


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Microseconds(-value)


	companion object /* : TimeMeasurement.CompanionInterface<Microseconds> */ {

		/* override */ val max = Microseconds(Long.MAX_VALUE)
		/* override */ val min = Microseconds(Long.MIN_VALUE)
		val perMillisecond = Microseconds(1_000L)
		val perSecond = Milliseconds.perSecond.toMicroseconds()
		val perMinute = Seconds.perMinute.toMicroseconds()
		val perHour = Minutes.perHour.toMicroseconds()
		val perDay = Hours.perDay.toMicroseconds()
		/* override */ val zero = Microseconds(0L)
	}
}


@ExperimentalTime
inline fun Duration.toMicroseconds() =
	Microseconds(inMicroseconds.toLong())


inline operator fun Int.times(other: Microseconds) =
	other.times(this)


inline operator fun Long.times(other: Microseconds) =
	other.times(this)
