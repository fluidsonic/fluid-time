package com.github.fluidsonic.fluid.time


interface TimeMeasurement<Measurement : TimeMeasurement<Measurement>> : TemporalMeasurement<Measurement> {

	fun toDays(): Days
	fun toDuration(): Duration
	fun toHours(): Hours
	fun toMicroseconds(): Microseconds
	fun toMilliseconds(): Milliseconds
	fun toMinutes(): Minutes
	fun toNanoseconds(): Nanoseconds
	fun toSeconds(): Seconds


	companion object


	interface CompanionInterface<Measurement : TimeMeasurement<Measurement>> : TemporalMeasurement.CompanionInterface<Measurement>
}
