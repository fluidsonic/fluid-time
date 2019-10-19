@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class DayOfMonth @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<DayOfMonth, Days> {

	override inline fun compareTo(other: DayOfMonth) =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override inline operator fun minus(other: DayOfMonth) =
		Days(toLong() - other.toLong())


	override inline operator fun minus(other: Days) =
		map { it - other.toLong() }


	override inline operator fun plus(other: Days) =
		map { it + other.toLong() }


	override inline fun toInt() =
		value.toInt()


	override inline fun toLong() =
		value.toLong()


	override inline fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<DayOfMonth> {

		/* override */ val max = unchecked(31)
		/* override */ val min = unchecked(1)


		override inline fun of(value: Long): DayOfMonth {
			check(value, inRange = min.toLong() .. max.toLong(), name = "day [of month]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			DayOfMonth(value.toByte())
	}
}
