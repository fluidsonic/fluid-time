@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlin.math.*


public inline class Years(@PublishedApi internal val value: Long) :
	TemporalMeasurement.LongBased<Years>,
	DateMeasurement<Years> {

	public constructor(value: Int) : this(value.toLong())


	override inline val absolute: Years
		get() = map(::abs) // cannot use Long::absoluteValue - https://youtrack.jetbrains.com/issue/KT-34469


	override inline fun compareTo(other: Years): Int =
		value.compareTo(other.value)


	override inline operator fun div(other: Int): Years =
		div(other.toLong())


	override inline operator fun div(other: Long): Years =
		Years(value / other)


	override inline operator fun div(other: Years): Long =
		value / other.value


	override inline val isNegative: Boolean
		get() = value < 0


	override inline val isPositive: Boolean
		get() = value > 0


	override inline val isZero: Boolean
		get() = value == 0L


	override inline fun map(transform: (Long) -> Long): Years =
		Years(transform(toLong()))


	override inline operator fun minus(other: Years): Years =
		Years(value - other.value)


	override inline operator fun plus(other: Years): Years =
		Years(value + other.value)


	override inline operator fun rem(other: Int): Years =
		rem(other.toLong())


	override inline operator fun rem(other: Long): Years =
		Years(value % other)


	override inline operator fun rem(other: Years): Years =
		Years(value % other.value)


	override inline operator fun times(other: Int): Years =
		times(other.toLong())


	override inline operator fun times(other: Long): Years =
		Years(value * other)


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value


	override inline fun toString(): String =
		value.toString()


	override inline operator fun unaryMinus(): Years =
		Years(-value)


	public companion object : DateMeasurement.CompanionInterface<Years> {

		/* override */public val max: Years = Years(Long.MAX_VALUE)
		/* override */public val min: Years = Years(Long.MIN_VALUE)
		/* override */public val zero: Years = Years(0L)
	}
}


public inline operator fun Int.times(other: Years): Years =
	other.times(this)


public inline operator fun Long.times(other: Years): Years =
	other.times(this)
