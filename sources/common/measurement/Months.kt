@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*


inline class Months(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Months>,
	DateMeasurement<Months> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Months) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Months(value / other)


	override inline operator fun div(other: Months) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isPositive
		get() = value > 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Months(transform(toLong()))


	override inline operator fun minus(other: Months) =
		Months(value - other.value)


	override inline operator fun plus(other: Months) =
		Months(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Months(value % other)


	override inline operator fun rem(other: Months) =
		Months(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Months(value * other)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Months(-value)


	companion object : DateMeasurement.CompanionInterface<Months> {

		/* override */ val max = Months(Long.MAX_VALUE)
		/* override */ val min = Months(Long.MIN_VALUE)
		/* override */ val zero = Months(0L)
	}
}


inline operator fun Int.times(other: Months) =
	other.times(this)


inline operator fun Long.times(other: Months) =
	other.times(this)
