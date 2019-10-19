@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class MinuteOfHour @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<MinuteOfHour, Minutes> {

	override inline fun compareTo(other: MinuteOfHour) =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override inline operator fun minus(other: MinuteOfHour) =
		Minutes(toLong() - other.toLong())


	override inline operator fun minus(other: Minutes) =
		map { it - other.toLong() }


	override inline operator fun plus(other: Minutes) =
		map { it + other.toLong() }


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value.toLong()


	override inline fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<MinuteOfHour> {

		/* override */ val max = unchecked(59)
		/* override */ val min = unchecked(0)


		override inline fun of(value: Long): MinuteOfHour {
			check(value, inRange = min.toLong() .. max.toLong(), name = "minute [of hour]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			MinuteOfHour(value.toByte())
	}
}
