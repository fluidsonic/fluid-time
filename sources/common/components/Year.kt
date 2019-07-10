package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class Year internal constructor(private val value: Int) : DateTimeComponent<Year, Years> {

	override fun compareTo(other: Year) =
		value.compareTo(other.value)


	val isLeap
		get() = value % 4 == 0 && (value % 100 != 0 || value % 400 == 0)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Year) =
		Years(toLong() - other.toLong())


	override fun minus(other: Years) =
		map { it - other.toLong() }


	override fun plus(other: Years) =
		map { it + other.toLong() }


	override fun toInt() =
		value


	override fun toLong() =
		value.toLong()


	override fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<Year> {

		override val max = unchecked(999_999_999)
		override val min = unchecked(-999_999_999)


		override fun of(value: Long): Year {
			check(value, inRange = min.toLong() .. max.toLong(), name = "year")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			Year(value.toInt())
	}
}
