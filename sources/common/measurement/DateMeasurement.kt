package io.fluidsonic.time


public interface DateMeasurement<Measurement : DateMeasurement<Measurement>> : TemporalMeasurement<Measurement> {

	public companion object;


	public interface CompanionInterface<Measurement : DateMeasurement<Measurement>> : TemporalMeasurement.CompanionInterface<Measurement>
}
