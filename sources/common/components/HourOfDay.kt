@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class HourOfDay @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<HourOfDay, Hours> {

	override inline fun compareTo(other: HourOfDay) =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override inline operator fun minus(other: HourOfDay) =
		Hours(toLong() - other.toLong())


	override inline operator fun minus(other: Hours) =
		map { it - other.toLong() }


	override inline operator fun plus(other: Hours) =
		map { it + other.toLong() }


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value.toLong()


	override inline fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<HourOfDay> {

		/* override */ val max = unchecked(23)
		/* override */ val min = unchecked(0)


		override inline fun of(value: Long): HourOfDay {
			check(value, inRange = min.toLong() .. max.toLong(), name = "hour [of day]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			HourOfDay(value.toByte())
	}
}
