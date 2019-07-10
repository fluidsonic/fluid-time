package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class HourOfDay internal constructor(private val value: Byte) : DateTimeComponent<HourOfDay, Hours> {

	override fun compareTo(other: HourOfDay) =
		value.compareTo(other.value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: HourOfDay) =
		Hours(toLong() - other.toLong())


	override fun minus(other: Hours) =
		map { it - other.toLong() }


	override fun plus(other: Hours) =
		map { it + other.toLong() }


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value.toLong()


	override fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<HourOfDay> {

		override val max = unchecked(23)
		override val min = unchecked(0)


		override fun of(value: Long): HourOfDay {
			check(value, inRange = min.toLong() .. max.toLong(), name = "hour [of day]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			HourOfDay(value.toByte())
	}
}
