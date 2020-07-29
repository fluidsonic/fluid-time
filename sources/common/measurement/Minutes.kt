@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*
import kotlin.time.*
import kotlin.time.Duration


public inline class Minutes(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Minutes>,
	TimeMeasurement<Minutes> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Minutes
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Minutes): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Minutes =
		div(other.toLong())


	override inline operator fun div(other: Long): Minutes =
		Minutes(value / other)


	override inline operator fun div(other: Minutes): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Minutes =
		Minutes(transform(toLong()))


	override inline operator fun minus(other: Minutes): Minutes =
		Minutes(value - other.value)


	override inline operator fun plus(other: Minutes): Minutes =
		Minutes(value + other.value)


	override inline operator fun rem(other: Int): Minutes =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Minutes =
		Minutes(value % other)


	override inline operator fun rem(other: Minutes): Minutes =
		Minutes(value % other.value)


	override inline operator fun times(other: Int): Minutes =
		times(other.toLong())


	override inline operator fun times(other: Long): Minutes =
		Minutes(value * other)


	override inline fun toDays(): Days =
		Days(this / perDay)


	@ExperimentalTime
	override inline fun toDuration(): Duration =
		value.minutes


	override inline fun toHours(): Hours =
		Hours(this / perHour)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toMicroseconds(): Microseconds =
		Microseconds.perMinute * value


	override inline fun toMilliseconds(): Milliseconds =
		Milliseconds.perMinute * value


	@Deprecated(message = "redundant conversion", level = DeprecationLevel.HIDDEN)
	override inline fun toMinutes(): Minutes =
		this


	override inline fun toNanoseconds(): Nanoseconds =
		Nanoseconds.perMinute * value


	override inline fun toPreciseDuration(): PreciseDuration =
		PreciseDuration.of(minutes = this)


	override inline fun toSeconds(): Seconds =
		Seconds.perMinute * value


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Minutes =
		Minutes(-value)


	public companion object : TimeMeasurement.CompanionInterface<Minutes> {

		/* override */public val max: Minutes = Minutes(Long.MAX_VALUE)
		/* override */public val min: Minutes = Minutes(Long.MIN_VALUE)
		public val perHour: Minutes = Minutes(60L)
		public val perDay: Minutes = Minutes(1_440L)
		/* override */public val zero: Minutes = Minutes(0L)
	}
}


@ExperimentalTime
public inline fun Duration.toMinutes(): Minutes =
	Minutes(inMinutes.toLong())


public inline operator fun Int.times(other: Minutes): Minutes =
	other.times(this)


public inline operator fun Long.times(other: Minutes): Minutes =
	other.times(this)
