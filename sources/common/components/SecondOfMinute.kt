@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class SecondOfMinute @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<SecondOfMinute, Seconds> {

	override fun compareTo(other: SecondOfMinute) =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override inline operator fun minus(other: SecondOfMinute) =
		Seconds(toLong() - other.toLong())


	override inline operator fun minus(other: Seconds) =
		map { it - other.toLong() }


	override inline operator fun plus(other: Seconds) =
		map { it + other.toLong() }


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value.toLong()


	override inline fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<SecondOfMinute> {

		/* override */ val max = unchecked(59)
		/* override */ val min = unchecked(0)


		override inline fun of(value: Long): SecondOfMinute {
			check(value, inRange = min.toLong() .. max.toLong(), name = "second [of minute]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			SecondOfMinute(value.toByte())
	}
}
