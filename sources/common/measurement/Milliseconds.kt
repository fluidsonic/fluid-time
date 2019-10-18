@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Milliseconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Milliseconds>,
	TimeMeasurement<Milliseconds> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Milliseconds) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Milliseconds(value / other)


	override inline operator fun div(other: Milliseconds) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Milliseconds(transform(toLong()))


	override inline operator fun minus(other: Milliseconds) =
		Milliseconds(value - other.value)


	override inline operator fun plus(other: Milliseconds) =
		Milliseconds(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Milliseconds(value % other)


	override inline operator fun rem(other: Milliseconds) =
		Milliseconds(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Milliseconds(value * other)


	override inline fun toDays() =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration() =
		value.milliseconds


	override inline fun toHours() =
		Hours(this / perHour)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toMicroseconds() =
		Microseconds.perMillisecond * value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toMilliseconds() =
		this


	override inline fun toMinutes() =
		Minutes(this / perMinute)


	override inline fun toNanoseconds() =
		Nanoseconds.perMillisecond * value


	override inline fun toPreciseDuration() =
		PreciseDuration.of(milliseconds = this)


	override inline fun toSeconds() =
		Seconds(this / perSecond)


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Milliseconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Milliseconds> {

		/* override */ val max = Milliseconds(Long.MAX_VALUE)
		/* override */ val min = Milliseconds(Long.MIN_VALUE)
		val perSecond = Milliseconds(1_000L)
		val perMinute = Seconds.perMinute.toMilliseconds()
		val perHour = Minutes.perHour.toMilliseconds()
		val perDay = Hours.perDay.toMilliseconds()
		/* override */ val zero = Milliseconds(0L)
	}
}


@ExperimentalTime
inline fun Duration.toMilliseconds() =
	Milliseconds(inMilliseconds.toLong())


inline operator fun Int.times(other: Milliseconds) =
	other.times(this)


inline operator fun Long.times(other: Milliseconds) =
	other.times(this)
