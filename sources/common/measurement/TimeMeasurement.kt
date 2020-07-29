package io.fluidsonic.time

import kotlin.time.*
import kotlin.time.Duration


public interface TimeMeasurement<Measurement : TimeMeasurement<Measurement>> : TemporalMeasurement<Measurement> {

	public fun toDays(): Days

	@ExperimentalTime
	public fun toDuration(): Duration

	public fun toHours(): Hours

	public fun toMicroseconds(): Microseconds

	public fun toMilliseconds(): Milliseconds

	public fun toMinutes(): Minutes

	public fun toNanoseconds(): Nanoseconds

	public fun toPreciseDuration(): PreciseDuration

	public fun toSeconds(): Seconds


	public companion object;


	public interface CompanionInterface<Measurement : TimeMeasurement<Measurement>> : TemporalMeasurement.CompanionInterface<Measurement>
}
