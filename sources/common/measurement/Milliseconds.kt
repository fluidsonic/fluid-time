@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Milliseconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Milliseconds>,
	TimeMeasurement<Milliseconds> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Milliseconds
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Milliseconds): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Milliseconds =
		div(other.toLong())


	override inline operator fun div(other: Long): Milliseconds =
		Milliseconds(value / other)


	override inline operator fun div(other: Milliseconds): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Milliseconds =
		Milliseconds(transform(toLong()))


	override inline operator fun minus(other: Milliseconds): Milliseconds =
		Milliseconds(value - other.value)


	override inline operator fun plus(other: Milliseconds): Milliseconds =
		Milliseconds(value + other.value)


	override inline operator fun rem(other: Int): Milliseconds =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Milliseconds =
		Milliseconds(value % other)


	override inline operator fun rem(other: Milliseconds): Milliseconds =
		Milliseconds(value % other.value)


	override inline operator fun times(other: Int): Milliseconds =
		times(other.toLong())


	override inline operator fun times(other: Long): Milliseconds =
		Milliseconds(value * other)


	override inline fun toDays(): Days =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.milliseconds


	override inline fun toHours(): Hours =
		Hours(this / perHour)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toMicroseconds(): Microseconds =
		Microseconds.perMillisecond * value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toMilliseconds(): Milliseconds =
		this


	override inline fun toMinutes(): Minutes =
		Minutes(this / perMinute)


	override inline fun toNanoseconds(): Nanoseconds =
		Nanoseconds.perMillisecond * value


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(milliseconds = this)


	override inline fun toSeconds(): Seconds =
		Seconds(this / perSecond)


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Milliseconds =
		Milliseconds(-value)


	public companion object : TimeMeasurement.CompanionInterface<Milliseconds> {

		/* override */public val max: Milliseconds = Milliseconds(Long.MAX_VALUE)
		/* override */public val min: Milliseconds = Milliseconds(Long.MIN_VALUE)
		public val perDay: Milliseconds = Milliseconds(86_400_000L)
		public val perHour: Milliseconds = Milliseconds(3_600_000L)
		public val perMinute: Milliseconds = Milliseconds(60_000L)
		public val perSecond: Milliseconds = Milliseconds(1_000L)
		/* override */ public val zero: Milliseconds = Milliseconds(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toMilliseconds(): Milliseconds =
	Milliseconds(inMilliseconds.toLong())


public inline operator fun Int.times(other: Milliseconds): Milliseconds =
	other.times(this)


public inline operator fun Long.times(other: Milliseconds): Milliseconds =
	other.times(this)
