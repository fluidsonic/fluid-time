package com.github.fluidsonic.fluid.time


interface DateMeasurement<Measurement : DateMeasurement<Measurement>> : TemporalMeasurement<Measurement> {

	companion object


	interface CompanionInterface<Measurement : DateMeasurement<Measurement>> : TemporalMeasurement.CompanionInterface<Measurement>
}
