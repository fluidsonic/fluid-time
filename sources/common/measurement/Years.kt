package com.github.fluidsonic.fluid.time

import kotlin.math.*


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class Years(override val value: Long) : DurationMeasurement.Basic<Years>, DurationMeasurement.DateBased<Years> {

	constructor(value: Int) : this(value.toLong())


	override val absolute
		get() = map(Long::absoluteValue)


	override fun compareTo(other: Years) =
		value.compareTo(other.value)


	override operator fun div(other: Int) =
		div(other.toLong())


	override operator fun div(other: Long) =
		Years(value / other)


	override operator fun div(other: Years) =
		value / other.value


	override val isNegative
		get() = value < 0


	override val isZero
		get() = value == 0L


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		Years(transform(value))


	override operator fun minus(other: Years) =
		Years(value - other.value)


	override operator fun plus(other: Years) =
		Years(value + other.value)


	override operator fun rem(other: Int) =
		rem(other.toLong())


	override operator fun rem(other: Long) =
		Years(value % other)


	override operator fun rem(other: Years) =
		Years(value % other.value)


	override operator fun times(other: Int) =
		times(other.toLong())


	override operator fun times(other: Long) =
		Years(value * other)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toString() =
		value.toString()


	override operator fun unaryMinus() =
		Years(-value)


	companion object {

		val zero = Years(0L)
	}
}


operator fun Int.times(other: Years) =
	other.times(this)


operator fun Long.times(other: Years) =
	other.times(this)
