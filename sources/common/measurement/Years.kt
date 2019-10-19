@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*


inline class Years(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Years>,
	DateMeasurement<Years> {

	constructor(value: Int) : this(value.toLong())


	override inline val absolute
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Years) =
		value.compareTo(other.value)


	override inline operator fun div(other: Int) =
		div(other.toLong())


	override inline operator fun div(other: Long) =
		Years(value / other)


	override inline operator fun div(other: Years) =
		value / other.value


	override inline val isNegative
		get() = value < 0


	override inline val isPositive
		get() = value > 0


	override inline val isZero
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long) =
		Years(transform(toLong()))


	override inline operator fun minus(other: Years) =
		Years(value - other.value)


	override inline operator fun plus(other: Years) =
		Years(value + other.value)


	override inline operator fun rem(other: Int) =
		rem(other.toLong())


	override inline operator fun rem(other: Long) =
		Years(value % other)


	override inline operator fun rem(other: Years) =
		Years(value % other.value)


	override inline operator fun times(other: Int) =
		times(other.toLong())


	override inline operator fun times(other: Long) =
		Years(value * other)


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value


	override inline fun toString() =
		value.toString()


	override inline operator fun unaryMinus() =
		Years(-value)


	companion object : DateMeasurement.CompanionInterface<Years> {

		/* override */ val max = Years(Long.MAX_VALUE)
		/* override */ val min = Years(Long.MIN_VALUE)
		/* override */ val zero = Years(0L)
	}
}


inline operator fun Int.times(other: Years) =
	other.times(this)


inline operator fun Long.times(other: Years) =
	other.times(this)
