package io.fluidsonic.time

import kotlin.time.*
import kotlin.time.Duration


interface TimeMeasurement<Measurement : TimeMeasurement<Measurement>> : TemporalMeasurement<Measurement> {

	fun toDays(): Days

	@ExperimentalTime
	fun toDuration(): Duration

	fun toHours(): Hours

	fun toMicroseconds(): Microseconds

	fun toMilliseconds(): Milliseconds

	fun toMinutes(): Minutes

	fun toNanoseconds(): Nanoseconds

	fun toPreciseDuration(): PreciseDuration

	fun toSeconds(): Seconds


	companion object


	interface CompanionInterface<Measurement : TimeMeasurement<Measurement>> : TemporalMeasurement.CompanionInterface<Measurement>
}
