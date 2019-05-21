package com.github.fluidsonic.fluid.time

import kotlin.math.*


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class Milliseconds(override val value: Long) : DurationMeasurement.Basic<Milliseconds>, DurationMeasurement.TimeBased<Milliseconds> {

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
		Milliseconds(transform(value))


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


	companion object {

		val perSecond = Milliseconds(1_000L)
		val perMinute = Seconds.perMinute.toMilliseconds()
		val perHour = Minutes.perHour.toMilliseconds()
		val perDay = Hours.perDay.toMilliseconds()
		val zero = Milliseconds(0L)
	}
}


operator fun Int.times(other: Milliseconds) =
	other.times(this)


operator fun Long.times(other: Milliseconds) =
	other.times(this)
