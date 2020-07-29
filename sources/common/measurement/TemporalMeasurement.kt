package io.fluidsonic.time


public interface TemporalMeasurement<Measurement : TemporalMeasurement<Measurement>> : Comparable<Measurement> {

	public val absolute: Measurement
	public val isNegative: Boolean
	public val isPositive: Boolean
	public val isZero: Boolean

	public operator fun div(other: Int): Measurement
	public operator fun div(other: Long): Measurement
	public operator fun div(other: Measurement): Long
	public operator fun minus(other: Measurement): Measurement
	public operator fun plus(other: Measurement): Measurement
	public operator fun rem(other: Int): Measurement
	public operator fun rem(other: Long): Measurement
	public operator fun rem(other: Measurement): Measurement
	public operator fun times(other: Int): Measurement
	public operator fun times(other: Long): Measurement
	public operator fun unaryMinus(): Measurement

	public companion object;


	public interface CompanionInterface<Measurement : TemporalMeasurement<Measurement>> {

		// removed due to boxing
//		val max: Measurement
//		val min: Measurement
//		val zero: Measurement
	}


	public interface LongBased<Measurement : LongBased<Measurement>> : TemporalMeasurement<Measurement> {

		public fun map(transform: (Long) -> Long): Measurement
		public fun toInt(): Int
		public fun toLong(): Long
	}
}
