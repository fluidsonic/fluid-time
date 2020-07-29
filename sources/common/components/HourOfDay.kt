@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class HourOfDay @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<HourOfDay, Hours> {

	override inline fun compareTo(other: HourOfDay): Int =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long): HourOfDay =
		of(transform(toLong()))


	override inline operator fun minus(other: HourOfDay): Hours =
		Hours(toLong() - other.toLong())


	override inline operator fun minus(other: Hours): HourOfDay =
		map { it - other.toLong() }


	override inline operator fun plus(other: Hours): HourOfDay =
		map { it + other.toLong() }


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value.toLong()


	override inline fun toString(): String =
		value.toString()


	public companion object : DateTimeComponent.CompanionInterface<HourOfDay> {

		/* override */ public val max: HourOfDay = unchecked(23)
		/* override */ public val min: HourOfDay = unchecked(0)


		override inline fun of(value: Long): HourOfDay {
			check(value, inRange = min.toLong() .. max.toLong(), name = "hour [of day]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			HourOfDay(value.toByte())
	}
}
