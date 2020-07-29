@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*


public inline class Months(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Months>,
	DateMeasurement<Months> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Months
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Months): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Months =
		div(other.toLong())


	override inline operator fun div(other: Long): Months =
		Months(value / other)


	override inline operator fun div(other: Months): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Months =
		Months(transform(toLong()))


	override inline operator fun minus(other: Months): Months =
		Months(value - other.value)


	override inline operator fun plus(other: Months): Months =
		Months(value + other.value)


	override inline operator fun rem(other: Int): Months =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Months =
		Months(value % other)


	override inline operator fun rem(other: Months): Months =
		Months(value % other.value)


	override inline operator fun times(other: Int): Months =
		times(other.toLong())


	override inline operator fun times(other: Long): Months =
		Months(value * other)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Months =
		Months(-value)


	public companion object : DateMeasurement.CompanionInterface<Months> {

		/* override */ public val max: Months = Months(Long.MAX_VALUE)
		/* override */ public val min: Months = Months(Long.MIN_VALUE)
		/* override */ public val zero: Months = Months(0L)
	}
}


public inline operator fun Int.times(other: Months): Months =
	other.times(this)


public inline operator fun Long.times(other: Months): Months =
	other.times(this)
