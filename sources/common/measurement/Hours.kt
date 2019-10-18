@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Hours(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Hours>,
	TimeMeasurement<Hours> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Hours) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Hours(value / other)


	override inline operator fun div(other: Hours) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Hours(transform(toLong()))


	override inline operator fun minus(other: Hours) =
		Hours(value - other.value)


	override inline operator fun plus(other: Hours) =
		Hours(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Hours(value % other)


	override inline operator fun rem(other: Hours) =
		Hours(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Hours(value * other)


	override inline fun toDays() =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration() =
		value.hours


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toHours() =
		this


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toMicroseconds() =
		Microseconds.perHour * value


	override inline fun toMilliseconds() =
		Milliseconds.perHour * value


	override inline fun toMinutes() =
		Minutes.perHour * value


	override inline fun toNanoseconds() =
		Nanoseconds.perHour * value


	override inline fun toPreciseDuration() =
		PreciseDuration.of(hours = this)


	override inline fun toSeconds() =
		Seconds.perHour * value


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Hours(-value)


	companion object : TimeMeasurement.CompanionInterface<Hours> {

		/* override */ val max = Hours(Long.MAX_VALUE)
		/* override */ val min = Hours(Long.MIN_VALUE)
		val perDay = Hours(24L)
		/* override */ val zero = Hours(0L)
	}
}


@ExperimentalTime
inline fun Duration.toHours() =
	Hours(inHours.toLong())


inline operator fun Int.times(other: Hours) =
	other.times(this)


inline operator fun Long.times(other: Hours) =
	other.times(this)
