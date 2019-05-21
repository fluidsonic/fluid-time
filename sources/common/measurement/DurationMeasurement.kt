package com.github.fluidsonic.fluid.time


interface DurationMeasurement<Measurement : DurationMeasurement<Measurement>> : Comparable<Measurement> {

	val absolute: Measurement
	val isNegative: Boolean
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


	interface Basic<Measurement : Basic<Measurement>> : DurationMeasurement<Measurement> {

		val value: Long

		fun map(transform: (Long) -> Long): Measurement
		fun toInt(): Int
		fun toLong(): Long
	}


	interface DateBased<Measurement : DateBased<Measurement>> : DurationMeasurement<Measurement>


	interface TimeBased<Measurement : TimeBased<Measurement>> : DurationMeasurement<Measurement> {

		fun toDays(): Days
		fun toDuration(): Duration
		fun toHours(): Hours
		fun toMicroseconds(): Microseconds
		fun toMilliseconds(): Milliseconds
		fun toMinutes(): Minutes
		fun toNanoseconds(): Nanoseconds
		fun toSeconds(): Seconds
	}
}
