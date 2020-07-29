@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Hours(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Hours>,
	TimeMeasurement<Hours> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Hours
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Hours): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Hours =
		div(other.toLong())


	override inline operator fun div(other: Long): Hours =
		Hours(value / other)


	override inline operator fun div(other: Hours): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Hours =
		Hours(transform(toLong()))


	override inline operator fun minus(other: Hours): Hours =
		Hours(value - other.value)


	override inline operator fun plus(other: Hours): Hours =
		Hours(value + other.value)


	override inline operator fun rem(other: Int): Hours =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Hours =
		Hours(value % other)


	override inline operator fun rem(other: Hours): Hours =
		Hours(value % other.value)


	override inline operator fun times(other: Int): Hours =
		times(other.toLong())


	override inline operator fun times(other: Long): Hours =
		Hours(value * other)


	override inline fun toDays(): Days =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.hours


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toHours(): Hours =
		this


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toMicroseconds(): Microseconds =
		Microseconds.perHour * value


	override inline fun toMilliseconds(): Milliseconds =
		Milliseconds.perHour * value


	override inline fun toMinutes(): Minutes =
		Minutes.perHour * value


	override inline fun toNanoseconds(): Nanoseconds =
		Nanoseconds.perHour * value


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(hours = this)


	override inline fun toSeconds(): Seconds =
		Seconds.perHour * value


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Hours =
		Hours(-value)


	public companion object : TimeMeasurement.CompanionInterface<Hours> {

		/* override */ public val max: Hours = Hours(Long.MAX_VALUE)
		/* override */public val min: Hours = Hours(Long.MIN_VALUE)
		public val perDay: Hours = Hours(24L)
		/* override */ public val zero: Hours = Hours(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toHours(): Hours =
	Hours(inHours.toLong())


public inline operator fun Int.times(other: Hours): Hours =
	other.times(this)


public inline operator fun Long.times(other: Hours): Hours =
	other.times(this)
