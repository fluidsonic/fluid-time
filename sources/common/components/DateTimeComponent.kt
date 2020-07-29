package io.fluidsonic.time


public interface DateTimeComponent<Component : DateTimeComponent<Component, Measurement>, Measurement : TemporalMeasurement<Measurement>> : Comparable<Component> {

	public fun map(transform: (Long) -> Long): Component
	public operator fun minus(other: Measurement): Component
	public operator fun minus(other: Component): Measurement
	public operator fun plus(other: Measurement): Component
	public fun toInt(): Int
	public fun toLong(): Long

	public companion object;


	public interface CompanionInterface<Component : DateTimeComponent<Component, *>> {

		// removed due to boxing
//		val max: Component
//		val min: Component

		public fun of(value: Long): Component
	}
}
