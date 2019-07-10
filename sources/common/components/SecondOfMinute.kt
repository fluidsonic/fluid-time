package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class SecondOfMinute internal constructor(private val value: Byte) : DateTimeComponent<SecondOfMinute, Seconds> {

	override fun compareTo(other: SecondOfMinute) =
		value.compareTo(other.value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: SecondOfMinute) =
		Seconds(toLong() - other.toLong())


	override fun minus(other: Seconds) =
		map { it - other.toLong() }


	override fun plus(other: Seconds) =
		map { it + other.toLong() }


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value.toLong()


	override fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<SecondOfMinute> {

		override val max = unchecked(59)
		override val min = unchecked(0)


		override fun of(value: Long): SecondOfMinute {
			check(value, inRange = min.toLong() .. max.toLong(), name = "second [of minute]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			SecondOfMinute(value.toByte())
	}
}
