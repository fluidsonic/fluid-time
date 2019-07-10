package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class MinuteOfHour internal constructor(private val value: Byte) : DateTimeComponent<MinuteOfHour, Minutes> {

	override fun compareTo(other: MinuteOfHour) =
		value.compareTo(other.value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: MinuteOfHour) =
		Minutes(toLong() - other.toLong())


	override fun minus(other: Minutes) =
		map { it - other.toLong() }


	override fun plus(other: Minutes) =
		map { it + other.toLong() }


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value.toLong()


	override fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<MinuteOfHour> {

		override val max = unchecked(59)
		override val min = unchecked(0)


		override fun of(value: Long): MinuteOfHour {
			check(value, inRange = min.toLong() .. max.toLong(), name = "minute [of hour]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			MinuteOfHour(value.toByte())
	}
}
