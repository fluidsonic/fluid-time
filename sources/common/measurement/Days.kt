package com.github.fluidsonic.fluid.time

import kotlin.math.*


inline class Days(private val value: Long) :
	TemporalMeasurement.LongBased<Days>,
	DateMeasurement<Days>,
	TimeMeasurement<Days> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Days) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Days(value / other)


	override operator fun div(other: Days) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Days(transform(toLong()))


	override operator fun minus(other: Days) =
		Days(value - other.value)


	override operator fun plus(other: Days) =
		Days(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Days(value % other)


	override operator fun rem(other: Days) =
		Days(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Days(value * other)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override fun toDays() =
		this


	override fun toDuration() =
		Duration.of(days = this)


	override fun toHours() =
		Hours.perDay * value


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toMicroseconds() =
		Microseconds.perDay * value


	override fun toMilliseconds() =
		Milliseconds.perDay * value


	override fun toMinutes() =
		Minutes.perDay * value


	override fun toNanoseconds() =
		Nanoseconds.perDay * value


	override fun toSeconds() =
		Seconds.perDay * value


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Days(-value)


	companion object : DateMeasurement.CompanionInterface<Days>, TimeMeasurement.CompanionInterface<Days> {

		override val max = Days(Long.MAX_VALUE)
		override val min = Days(Long.MIN_VALUE)
		override val zero = Days(0L)
	}
}


operator fun Int.times(other: Days) =
	other.times(this)


operator fun Long.times(other: Days) =
	other.times(this)
