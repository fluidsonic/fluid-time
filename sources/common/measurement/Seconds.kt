@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


inline class Seconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Seconds>,
	TimeMeasurement<Seconds> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Seconds) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Seconds(value / other)


	override inline operator fun div(other: Seconds) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Seconds(transform(toLong()))


	override inline operator fun minus(other: Seconds) =
		Seconds(value - other.value)


	override inline operator fun plus(other: Seconds) =
		Seconds(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Seconds(value % other)


	override inline operator fun rem(other: Seconds) =
		Seconds(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Seconds(value * other)


	override inline fun toDays() =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration() =
		value.seconds


	override inline fun toHours() =
		Hours(this / perHour)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toMicroseconds() =
		Microseconds.perSecond * value


	override inline fun toMilliseconds() =
		Milliseconds.perSecond * value


	override inline fun toMinutes() =
		Minutes(this / perMinute)


	override inline fun toNanoseconds() =
		Nanoseconds.perSecond * value


	override inline fun toPreciseDuration() =
		PreciseDuration.of(seconds = this)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toSeconds() =
		this


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Seconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Seconds> {

		/* override */ val max = Seconds(Long.MAX_VALUE)
		/* override */ val min = Seconds(Long.MIN_VALUE)
		val perMinute = Seconds(60L)
		val perHour = Minutes.perHour.toSeconds()
		val perDay = Hours.perDay.toSeconds()
		/* override */ val zero = Seconds(0L)
	}
}


@ExperimentalTime
inline fun Duration.toSeconds() =
	Seconds(inSeconds.toLong())


inline operator fun Int.times(other: Seconds) =
	other.times(this)


inline operator fun Long.times(other: Seconds) =
	other.times(this)
