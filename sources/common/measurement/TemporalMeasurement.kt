package io.fluidsonic.time


interface TemporalMeasurement<Measurement : TemporalMeasurement<Measurement>> : Comparable<Measurement> {

	val absolute: Measurement
	val isNegative: Boolean
	val isPositive: Boolean
	val isZero: Boolean

	operator fun div(other: Int): Measurement
	operator fun div(other: Long): Measurement
	operator fun div(other: Measurement): Long
	operator fun minus(other: Measurement): Measurement
	operator fun plus(other: Measurement): Measurement
	operator fun rem(other: Int): Measurement
	operator fun rem(other: Long): Measurement
	operator fun rem(other: Measurement): Measurement
	operator fun times(other: Int): Measurement
	operator fun times(other: Long): Measurement
	operator fun unaryMinus(): Measurement

	companion object


	interface CompanionInterface<Measurement : TemporalMeasurement<Measurement>> {

		// removed due to boxing
//		val max: Measurement
//		val min: Measurement
//		val zero: Measurement
	}


	interface LongBased<Measurement : LongBased<Measurement>> : TemporalMeasurement<Measurement> {

		fun map(transform: (Long) -> Long): Measurement
		fun toInt(): Int
		fun toLong(): Long
	}
}
