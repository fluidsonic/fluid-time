@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class NanosecondOfSecond @PublishedApi internal constructor(@PublishedApi internal val value: Int) : DateTimeComponent<NanosecondOfSecond, Nanoseconds> {

	override inline fun compareTo(other: NanosecondOfSecond) =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override inline operator fun minus(other: NanosecondOfSecond) =
		Nanoseconds(toLong() - other.toLong())


	override inline operator fun minus(other: Nanoseconds) =
		map { it - other.toLong() }


	override inline operator fun plus(other: Nanoseconds) =
		map { it + other.toLong() }


	override inline fun toInt() =
		value


	override inline fun toLong() =
		value.toLong()


	override inline fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<NanosecondOfSecond> {

		/* override */ val max = unchecked(999_999_999)
		/* override */ val min = unchecked(0)


		override inline fun of(value: Long): NanosecondOfSecond {
			check(value, inRange = min.toLong() .. max.toLong(), name = "nanosecond [of second]")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			NanosecondOfSecond(value.toInt())
	}
}
