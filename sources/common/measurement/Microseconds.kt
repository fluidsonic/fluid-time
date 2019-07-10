package com.github.fluidsonic.fluid.time

import kotlin.math.*


inline class Microseconds(private val value: Long) :
	TemporalMeasurement.LongBased<Microseconds>,
	TimeMeasurement<Microseconds> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Microseconds) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Microseconds(value / other)


	override operator fun div(other: Microseconds) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Microseconds(transform(toLong()))


	override operator fun minus(other: Microseconds) =
		Microseconds(value - other.value)


	override operator fun plus(other: Microseconds) =
		Microseconds(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Microseconds(value % other)


	override operator fun rem(other: Microseconds) =
		Microseconds(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Microseconds(value * other)


	override fun toDays() =
		Days(this / perDay)


	override fun toDuration() =
		Duration.of(microseconds = this)


	override fun toHours() =
		Hours(this / perHour)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override fun toMicroseconds() =
		this


	override fun toMilliseconds() =
		Milliseconds(this / perMillisecond)


	override fun toMinutes() =
		Minutes(this / perMinute)


	override fun toNanoseconds() =
		Nanoseconds.perMicrosecond * value


	override fun toSeconds() =
		Seconds(this / perSecond)


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Microseconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Microseconds> {

		override val max = Microseconds(Long.MAX_VALUE)
		override val min = Microseconds(Long.MIN_VALUE)
		val perMillisecond = Microseconds(1_000L)
		val perSecond = Milliseconds.perSecond.toMicroseconds()
		val perMinute = Seconds.perMinute.toMicroseconds()
		val perHour = Minutes.perHour.toMicroseconds()
		val perDay = Hours.perDay.toMicroseconds()
		override val zero = Microseconds(0L)
	}
}


operator fun Int.times(other: Microseconds) =
	other.times(this)


operator fun Long.times(other: Microseconds) =
	other.times(this)
