package com.github.fluidsonic.fluid.time

import kotlin.math.*


inline class Seconds(private val value: Long) :
	TemporalMeasurement.LongBased<Seconds>,
	TimeMeasurement<Seconds> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Seconds) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Seconds(value / other)


	override operator fun div(other: Seconds) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Seconds(transform(toLong()))


	override operator fun minus(other: Seconds) =
		Seconds(value - other.value)


	override operator fun plus(other: Seconds) =
		Seconds(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Seconds(value % other)


	override operator fun rem(other: Seconds) =
		Seconds(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Seconds(value * other)


	override fun toDays() =
		Days(this / perDay)


	override fun toDuration() =
		Duration.of(seconds = this)


	override fun toHours() =
		Hours(this / perHour)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toMicroseconds() =
		Microseconds.perSecond * value


	override fun toMilliseconds() =
		Milliseconds.perSecond * value


	override fun toMinutes() =
		Minutes(this / perMinute)


	override fun toNanoseconds() =
		Nanoseconds.perSecond * value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override fun toSeconds() =
		this


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Seconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Seconds> {

		override val max = Seconds(Long.MAX_VALUE)
		override val min = Seconds(Long.MIN_VALUE)
		val perMinute = Seconds(60L)
		val perHour = Minutes.perHour.toSeconds()
		val perDay = Hours.perDay.toSeconds()
		override val zero = Seconds(0L)
	}
}


operator fun Int.times(other: Seconds) =
	other.times(this)


operator fun Long.times(other: Seconds) =
	other.times(this)
