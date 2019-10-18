@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Minutes(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Minutes>,
	TimeMeasurement<Minutes> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Minutes) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Minutes(value / other)


	override inline operator fun div(other: Minutes) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isPositive
		get() = value > 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Minutes(transform(toLong()))


	override inline operator fun minus(other: Minutes) =
		Minutes(value - other.value)


	override inline operator fun plus(other: Minutes) =
		Minutes(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Minutes(value % other)


	override inline operator fun rem(other: Minutes) =
		Minutes(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Minutes(value * other)


	override inline fun toDays() =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration() =
		value.minutes


	override inline fun toHours() =
		Hours(this / perHour)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toMicroseconds() =
		Microseconds.perMinute * value


	override inline fun toMilliseconds() =
		Milliseconds.perMinute * value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toMinutes() =
		this


	override inline fun toNanoseconds() =
		Nanoseconds.perMinute * value


	override inline fun toPreciseDuration() =
		PreciseDuration.of(minutes = this)


	override inline fun toSeconds() =
		Seconds.perMinute * value


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Minutes(-value)


	companion object : TimeMeasurement.CompanionInterface<Minutes> {

		/* override */ val max = Minutes(Long.MAX_VALUE)
		/* override */ val min = Minutes(Long.MIN_VALUE)
		val perHour = Minutes(60L)
		val perDay = Minutes(1_440L)
		/* override */ val zero = Minutes(0L)
	}
}


@ExperimentalTime
inline fun Duration.toMinutes() =
	Minutes(inMinutes.toLong())


inline operator fun Int.times(other: Minutes) =
	other.times(this)


inline operator fun Long.times(other: Minutes) =
	other.times(this)
