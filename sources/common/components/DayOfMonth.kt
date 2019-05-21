package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class DayOfMonth internal constructor(private val _value: Byte) : DateTimeComponent<DayOfMonth, Days> {

	override fun compareTo(other: DayOfMonth) =
		_value.compareTo(other._value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Days(other))


	override fun minus(other: DayOfMonth) =
		Days(value - other.value)


	override fun minus(other: Days) =
		map { it - other.value }


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Days(other))


	override fun plus(other: Days) =
		map { it + other.value }


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toString() =
		_value.toString()


	val value
		get() = _value.toLong()


	companion object {

		val max = unchecked(31)
		val min = unchecked(1)


		fun of(value: Long): DayOfMonth {
			check(value, inRange = min.value .. max.value, name = "day [of month]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			DayOfMonth(value.toByte())
	}
}
