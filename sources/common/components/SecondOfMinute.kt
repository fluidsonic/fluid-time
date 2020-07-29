@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class SecondOfMinute @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<SecondOfMinute, Seconds> {

	override fun compareTo(other: SecondOfMinute): Int =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long): SecondOfMinute =
		of(transform(toLong()))


	override inline operator fun minus(other: SecondOfMinute): Seconds =
		Seconds(toLong() - other.toLong())


	override inline operator fun minus(other: Seconds): SecondOfMinute =
		map { it - other.toLong() }


	override inline operator fun plus(other: Seconds): SecondOfMinute =
		map { it + other.toLong() }


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value.toLong()


	override inline fun toString(): String =
		value.toString()


	public companion object : DateTimeComponent.CompanionInterface<SecondOfMinute> {

		/* override */ public val max: SecondOfMinute = unchecked(59)
		/* override */ public val min: SecondOfMinute = unchecked(0)


		override inline fun of(value: Long): SecondOfMinute {
			check(value, inRange = min.toLong() .. max.toLong(), name = "second [of minute]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			SecondOfMinute(value.toByte())
	}
}
