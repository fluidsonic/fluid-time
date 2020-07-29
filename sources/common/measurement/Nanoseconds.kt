@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Nanoseconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Nanoseconds>,
	TimeMeasurement<Nanoseconds> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Nanoseconds
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Nanoseconds): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Nanoseconds =
		div(other.toLong())


	override inline operator fun div(other: Long): Nanoseconds =
		Nanoseconds(value / other)


	override inline operator fun div(other: Nanoseconds): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Nanoseconds =
		Nanoseconds(transform(toLong()))


	override inline operator fun minus(other: Nanoseconds): Nanoseconds =
		Nanoseconds(value - other.value)


	override inline operator fun plus(other: Nanoseconds): Nanoseconds =
		Nanoseconds(value + other.value)


	override inline operator fun rem(other: Int): Nanoseconds =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Nanoseconds =
		Nanoseconds(value % other)


	override inline operator fun rem(other: Nanoseconds): Nanoseconds =
		Nanoseconds(value % other.value)


	override inline operator fun times(other: Int): Nanoseconds =
		times(other.toLong())


	override inline operator fun times(other: Long): Nanoseconds =
		Nanoseconds(value * other)


	override inline fun toDays(): Days =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.nanoseconds


	override inline fun toHours(): Hours =
		Hours(this / perHour)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toMicroseconds(): Microseconds =
		Microseconds(this / perMicrosecond)


	override inline fun toMilliseconds(): Milliseconds =
		Milliseconds(this / perMillisecond)


	override inline fun toMinutes(): Minutes =
		Minutes(this / perMinute)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toNanoseconds(): Nanoseconds =
		this


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(nanoseconds = this)


	override inline fun toSeconds(): Seconds =
		Seconds(this / perSecond)


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Nanoseconds =
		Nanoseconds(-value)


	public companion object : TimeMeasurement.CompanionInterface<Nanoseconds> {

		/* override */ public val max: Nanoseconds = Nanoseconds(Long.MAX_VALUE)
		/* override */ public val min: Nanoseconds = Nanoseconds(Long.MIN_VALUE)
		public val perDay: Nanoseconds = Nanoseconds(86_400_000_000_000L)
		public val perHour: Nanoseconds = Nanoseconds(3_600_000_000_000L)
		public val perMicrosecond: Nanoseconds = Nanoseconds(1_000L)
		public val perMillisecond: Nanoseconds = Nanoseconds(1_000_000L)
		public val perMinute: Nanoseconds = Nanoseconds(60_000_000_000L)
		public val perSecond: Nanoseconds = Nanoseconds(1_000_000_000L)
		/* override */ public val zero: Nanoseconds = Nanoseconds(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toNanoseconds(): Nanoseconds =
	Nanoseconds(inNanoseconds.toLong())


public inline operator fun Int.times(other: Nanoseconds): Nanoseconds =
	other.times(this)


public inline operator fun Long.times(other: Nanoseconds): Nanoseconds =
	other.times(this)
