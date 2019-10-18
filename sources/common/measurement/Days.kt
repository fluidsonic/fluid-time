@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Days(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Days>,
	DateMeasurement<Days>,
	TimeMeasurement<Days> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Days) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Days(value / other)


	override inline operator fun div(other: Days) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Days(transform(toLong()))


	override inline operator fun minus(other: Days) =
		Days(value - other.value)


	override inline operator fun plus(other: Days) =
		Days(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Days(value % other)


	override inline operator fun rem(other: Days) =
		Days(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Days(value * other)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toDays() =
		this


	@ExperimentalTime
	override inline fun toDuration() =
		value.days


	override inline fun toHours() =
		Hours.perDay * value


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toMicroseconds() =
		Microseconds.perDay * value


	override inline fun toMilliseconds() =
		Milliseconds.perDay * value


	override inline fun toMinutes() =
		Minutes.perDay * value


	override inline fun toNanoseconds() =
		Nanoseconds.perDay * value


	override inline fun toPreciseDuration() =
		PreciseDuration.of(days = this)


	override inline fun toSeconds() =
		Seconds.perDay * value


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Days(-value)


	companion object : DateMeasurement.CompanionInterface<Days>, TimeMeasurement.CompanionInterface<Days> {

		/* override */ val max = Days(Long.MAX_VALUE)
		/* override */ val min = Days(Long.MIN_VALUE)
		/* override */ val zero = Days(0L)
	}
}


@ExperimentalTime
inline fun Duration.toDays() =
	Days(inDays.toLong())


inline operator fun Int.times(other: Days) =
	other.times(this)


inline operator fun Long.times(other: Days) =
	other.times(this)
