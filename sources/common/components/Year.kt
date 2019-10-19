@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class Year @PublishedApi internal constructor(@PublishedApi internal val value: Int) : DateTimeComponent<Year, Years> {

	override inline fun compareTo(other: Year) =
		value.compareTo(other.value)


	val isLeap
		get() = value % 4 == 0 && (value % 100 != 0 || value % 400 == 0)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override inline operator fun minus(other: Year) =
		Years(toLong() - other.toLong())


	override inline operator fun minus(other: Years) =
		map { it - other.toLong() }


	override inline operator fun plus(other: Years) =
		map { it + other.toLong() }


	override inline fun toInt() =
		value


	override inline fun toLong() =
		value.toLong()


	override inline fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<Year> {

		/* override */ val max = unchecked(999_999_999)
		/* override */ val min = unchecked(-999_999_999)


		override inline fun of(value: Long): Year {
			check(value, inRange = min.toLong() .. max.toLong(), name = "year")

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			Year(value.toInt())
	}
}
