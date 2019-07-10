package com.github.fluidsonic.fluid.time


interface DateTimeComponent<Component : DateTimeComponent<Component, Measurement>, Measurement : TemporalMeasurement<Measurement>> : Comparable<Component> {

	fun map(transform: (Long) -> Long): Component
	operator fun minus(other: Measurement): Component
	operator fun minus(other: Component): Measurement
	operator fun plus(other: Measurement): Component
	fun toInt(): Int
	fun toLong(): Long

	companion object


	interface CompanionInterface<Component : DateTimeComponent<Component, *>> {

		val max: Component
		val min: Component

		fun of(value: Long): Component
	}
}
