package com.github.fluidsonic.fluid.time

import kotlin.math.*


inline class Nanoseconds(private val value: Long) :
	TemporalMeasurement.LongBased<Nanoseconds>,
	TimeMeasurement<Nanoseconds> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Nanoseconds) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Nanoseconds(value / other)


	override operator fun div(other: Nanoseconds) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Nanoseconds(transform(toLong()))


	override operator fun minus(other: Nanoseconds) =
		Nanoseconds(value - other.value)


	override operator fun plus(other: Nanoseconds) =
		Nanoseconds(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Nanoseconds(value % other)


	override operator fun rem(other: Nanoseconds) =
		Nanoseconds(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Nanoseconds(value * other)


	override fun toDays() =
		Days(this / perDay)


	override fun toDuration() =
		Duration.of(nanoseconds = this)


	override fun toHours() =
		Hours(this / perHour)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toMicroseconds() =
		Microseconds(this / perMicrosecond)


	override fun toMilliseconds() =
		Milliseconds(this / perMillisecond)


	override fun toMinutes() =
		Minutes(this / perMinute)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override fun toNanoseconds() =
		this


	override fun toSeconds() =
		Seconds(this / perSecond)


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Nanoseconds(-value)


	companion object : TimeMeasurement.CompanionInterface<Nanoseconds> {

		override val max = Nanoseconds(Long.MAX_VALUE)
		override val min = Nanoseconds(Long.MIN_VALUE)
		val perMicrosecond = Nanoseconds(1_000L)
		val perMillisecond = Microseconds.perMillisecond.toNanoseconds()
		val perSecond = Milliseconds.perSecond.toNanoseconds()
		val perMinute = Seconds.perMinute.toNanoseconds()
		val perHour = Minutes.perHour.toNanoseconds()
		val perDay = Hours.perDay.toNanoseconds()
		override val zero = Nanoseconds(0L)
	}
}


operator fun Int.times(other: Nanoseconds) =
	other.times(this)


operator fun Long.times(other: Nanoseconds) =
	other.times(this)
