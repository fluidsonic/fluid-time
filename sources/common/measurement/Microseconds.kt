@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Microseconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Microseconds>,
	TimeMeasurement<Microseconds> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Microseconds
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Microseconds): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Microseconds =
		div(other.toLong())


	override inline operator fun div(other: Long): Microseconds =
		Microseconds(value / other)


	override inline operator fun div(other: Microseconds): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Microseconds =
		Microseconds(transform(toLong()))


	override inline operator fun minus(other: Microseconds): Microseconds =
		Microseconds(value - other.value)


	override inline operator fun plus(other: Microseconds): Microseconds =
		Microseconds(value + other.value)


	override inline operator fun rem(other: Int): Microseconds =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Microseconds =
		Microseconds(value % other)


	override inline operator fun rem(other: Microseconds): Microseconds =
		Microseconds(value % other.value)


	override inline operator fun times(other: Int): Microseconds =
		times(other.toLong())


	override inline operator fun times(other: Long): Microseconds =
		Microseconds(value * other)


	override inline fun toDays(): Days =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.microseconds


	override inline fun toHours(): Hours =
		Hours(this / perHour)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toMicroseconds(): Microseconds =
		this


	override inline fun toMilliseconds(): Milliseconds =
		Milliseconds(this / perMillisecond)


	override inline fun toMinutes(): Minutes =
		Minutes(this / perMinute)


	override inline fun toNanoseconds(): Nanoseconds =
		Nanoseconds.perMicrosecond * value


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(microseconds = this)


	override inline fun toSeconds(): Seconds =
		Seconds(this / perSecond)


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Microseconds =
		Microseconds(-value)


	public companion object : TimeMeasurement.CompanionInterface<Microseconds> {

		/* override */ public val max: Microseconds = Microseconds(Long.MAX_VALUE)
		/* override */ public val min: Microseconds = Microseconds(Long.MIN_VALUE)
		public val perDay: Microseconds = Microseconds(86_400_000_000L)
		public val perHour: Microseconds = Microseconds(3_600_000_000L)
		public val perMillisecond: Microseconds = Microseconds(1_000L)
		public val perMinute: Microseconds = Microseconds(60_000_000L)
		public val perSecond: Microseconds = Microseconds(1_000_000L)
		/* override */ public val zero: Microseconds = Microseconds(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toMicroseconds(): Microseconds =
	Microseconds(inMicroseconds.toLong())


public inline operator fun Int.times(other: Microseconds): Microseconds =
	other.times(this)


public inline operator fun Long.times(other: Microseconds): Microseconds =
	other.times(this)
