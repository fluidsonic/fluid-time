package com.github.fluidsonic.fluid.time

import kotlin.math.*


inline class Months(private val value: Long) :
	TemporalMeasurement.LongBased<Months>,
	DateMeasurement<Months> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Months) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Months(value / other)


	override operator fun div(other: Months) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Months(transform(toLong()))


	override operator fun minus(other: Months) =
		Months(value - other.value)


	override operator fun plus(other: Months) =
		Months(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Months(value % other)


	override operator fun rem(other: Months) =
		Months(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Months(value * other)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Months(-value)


	companion object : DateMeasurement.CompanionInterface<Months> {

		override val max = Months(Long.MIN_VALUE)
		override val min = Months(Long.MAX_VALUE)
		override val zero = Months(0L)
	}
}


operator fun Int.times(other: Months) =
	other.times(this)


operator fun Long.times(other: Months) =
	other.times(this)
