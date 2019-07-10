package com.github.fluidsonic.fluid.time

import kotlin.math.*


inline class Milliseconds(private val value: Long) :
	TemporalMeasurement.LongBased<Milliseconds>,
	TimeMeasurement<Milliseconds> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Milliseconds) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Milliseconds(value / other)


	override operator fun div(other: Milliseconds) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Milliseconds(transform(toLong()))


	override operator fun minus(other: Milliseconds) =
		Milliseconds(value - other.value)


	override operator fun plus(other: Milliseconds) =
		Milliseconds(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Milliseconds(value % other)


	override operator fun rem(other: Milliseconds) =
		Milliseconds(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Milliseconds(value * other)


	override fun toDays() =
		Days(this / perDay)


	override fun toDuration() =
		Duration.of(milliseconds = this)


	override fun toHours() =
		Hours(this / perHour)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toMicroseconds() =
		Microseconds.perMillisecond * value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override fun toMilliseconds() =
		this


	override fun toMinutes() =
		Minutes(this / perMinute)


	override fun toNanoseconds() =
		Nanoseconds.perMillisecond * value


	override fun toSeconds() =
		Seconds(this / perSecond)


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Milliseconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Milliseconds> {

		override val max = Milliseconds(Long.MAX_VALUE)
		override val min = Milliseconds(Long.MIN_VALUE)
		val perSecond = Milliseconds(1_000L)
		val perMinute = Seconds.perMinute.toMilliseconds()
		val perHour = Minutes.perHour.toMilliseconds()
		val perDay = Hours.perDay.toMilliseconds()
		override val zero = Milliseconds(0L)
	}
}


operator fun Int.times(other: Milliseconds) =
	other.times(this)


operator fun Long.times(other: Milliseconds) =
	other.times(this)
