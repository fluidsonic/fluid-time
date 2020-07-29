@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Seconds(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Seconds>,
	TimeMeasurement<Seconds> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Seconds
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Seconds): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Seconds =
		div(other.toLong())


	override inline operator fun div(other: Long): Seconds =
		Seconds(value / other)


	override inline operator fun div(other: Seconds): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Seconds =
		Seconds(transform(toLong()))


	override inline operator fun minus(other: Seconds): Seconds =
		Seconds(value - other.value)


	override inline operator fun plus(other: Seconds): Seconds =
		Seconds(value + other.value)


	override inline operator fun rem(other: Int): Seconds =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Seconds =
		Seconds(value % other)


	override inline operator fun rem(other: Seconds): Seconds =
		Seconds(value % other.value)


	override inline operator fun times(other: Int): Seconds =
		times(other.toLong())


	override inline operator fun times(other: Long): Seconds =
		Seconds(value * other)


	override inline fun toDays(): Days =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.seconds


	override inline fun toHours(): Hours =
		Hours(this / perHour)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toMicroseconds(): Microseconds =
		Microseconds.perSecond * value


	override inline fun toMilliseconds(): Milliseconds =
		Milliseconds.perSecond * value


	override inline fun toMinutes(): Minutes =
		Minutes(this / perMinute)


	override inline fun toNanoseconds(): Nanoseconds =
		Nanoseconds.perSecond * value


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(seconds = this)


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toSeconds(): Seconds =
		this


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Seconds =
		Seconds(-value)


	public companion object : TimeMeasurement.CompanionInterface<Seconds> {

		/* override */ public val max: Seconds = Seconds(Long.MAX_VALUE)
		/* override */ public val min: Seconds = Seconds(Long.MIN_VALUE)
		public val perDay: Seconds = Seconds(86_400L)
		public val perHour: Seconds = Seconds(3_600L)
		public val perMinute: Seconds = Seconds(60L)
		/* override */ public val zero: Seconds = Seconds(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toSeconds(): Seconds =
	Seconds(inSeconds.toLong())


public inline operator fun Int.times(other: Seconds): Seconds =
	other.times(this)


public inline operator fun Long.times(other: Seconds): Seconds =
	other.times(this)
