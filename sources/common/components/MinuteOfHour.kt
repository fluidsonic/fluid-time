@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class MinuteOfHour @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<MinuteOfHour, Minutes> {

	override inline fun compareTo(other: MinuteOfHour): Int =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long): MinuteOfHour =
		of(transform(toLong()))


	override inline operator fun minus(other: MinuteOfHour): Minutes =
		Minutes(toLong() - other.toLong())


	override inline operator fun minus(other: Minutes): MinuteOfHour =
		map { it - other.toLong() }


	override inline operator fun plus(other: Minutes): MinuteOfHour =
		map { it + other.toLong() }


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value.toLong()


	override inline fun toString(): String =
		value.toString()


	public companion object : DateTimeComponent.CompanionInterface<MinuteOfHour> {

		/* override */ public val max: MinuteOfHour = unchecked(59)
		/* override */ public val min: MinuteOfHour = unchecked(0)


		override inline fun of(value: Long): MinuteOfHour {
			check(value, inRange = min.toLong() .. max.toLong(), name = "minute [of hour]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			MinuteOfHour(value.toByte())
	}
}
