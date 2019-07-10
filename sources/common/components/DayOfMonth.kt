package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class DayOfMonth internal constructor(private val value: Byte) : DateTimeComponent<DayOfMonth, Days> {

	override fun compareTo(other: DayOfMonth) =
		value.compareTo(other.value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: DayOfMonth) =
		Days(toLong() - other.toLong())


	override fun minus(other: Days) =
		map { it - other.toLong() }


	override fun plus(other: Days) =
		map { it + other.toLong() }


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value.toLong()


	override fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<DayOfMonth> {

		override val max = unchecked(31)
		override val min = unchecked(1)


		override fun of(value: Long): DayOfMonth {
			check(value, inRange = min.toLong() .. max.toLong(), name = "day [of month]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			DayOfMonth(value.toByte())
	}
}
