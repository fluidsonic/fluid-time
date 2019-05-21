package com.github.fluidsonic.fluid.time


interface DateTimeComponent<Component : DateTimeComponent<Component, Duration>, Duration : DurationMeasurement<Duration>> : Comparable<Component> {

	fun map(transform: (Long) -> Long): Component
	operator fun minus(other: Int): Component
	operator fun minus(other: Long): Component
	operator fun minus(other: Duration): Component
	operator fun minus(other: Component): Duration
	operator fun plus(other: Int): Component
	operator fun plus(other: Long): Component
	operator fun plus(other: Duration): Component
	fun toInt(): Int
	fun toLong(): Long

	companion object
}
