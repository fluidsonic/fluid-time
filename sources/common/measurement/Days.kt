@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Days(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Days>,
	DateMeasurement<Days>,
	TimeMeasurement<Days> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Days
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Days): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Days =
		div(other.toLong())


	override inline operator fun div(other: Long): Days =
		Days(value / other)


	override inline operator fun div(other: Days): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Days =
		Days(transform(toLong()))


	override inline operator fun minus(other: Days): Days =
		Days(value - other.value)


	override inline operator fun plus(other: Days): Days =
		Days(value + other.value)


	override inline operator fun rem(other: Int): Days =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Days =
		Days(value % other)


	override inline operator fun rem(other: Days): Days =
		Days(value % other.value)


	override inline operator fun times(other: Int): Days =
		times(other.toLong())


	override inline operator fun times(other: Long): Days =
		Days(value * other)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toDays(): Days =
		this


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.days


	override inline fun toHours(): Hours =
		Hours.perDay * value


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toMicroseconds(): Microseconds =
		Microseconds.perDay * value


	override inline fun toMilliseconds(): Milliseconds =
		Milliseconds.perDay * value


	override inline fun toMinutes(): Minutes =
		Minutes.perDay * value


	override inline fun toNanoseconds(): Nanoseconds =
		Nanoseconds.perDay * value


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(days = this)


	override inline fun toSeconds(): Seconds =
		Seconds.perDay * value


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Days =
		Days(-value)


	public companion object : DateMeasurement.CompanionInterface<Days>, TimeMeasurement.CompanionInterface<Days> {

		/* override */ public val max: Days = Days(Long.MAX_VALUE)
		/* override */ public val min: Days = Days(Long.MIN_VALUE)
		/* override */ public val zero: Days = Days(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toDays(): Days =
	Days(inDays.toLong())


public inline operator fun Int.times(other: Days): Days =
	other.times(this)


public inline operator fun Long.times(other: Days): Days =
	other.times(this)
