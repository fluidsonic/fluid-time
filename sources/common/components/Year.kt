package com.github.fluidsonic.fluid.time


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class Year internal constructor(private val _value: Int) : DateTimeComponent<Year, Years> {

	override fun compareTo(other: Year) =
		_value.compareTo(other._value)


	val isLeap
		get() = _value % 4 == 0 && (_value % 100 != 0 || _value % 400 == 0)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Years(other))


	override fun minus(other: Year) =
		Years(value - other.value)


	override fun minus(other: Years) =
		map { it - other.value }


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Years(other))


	override fun plus(other: Years) =
		map { it + other.value }


	override fun toInt() =
		_value


	override fun toLong() =
		value


	override fun toString() =
		_value.toString()


	val value
		get() = _value.toLong()


	companion object {

		val max = unchecked(999_999_999)
		val min = unchecked(-999_999_999)


		fun of(value: Long): Year {
			check(value, inRange = min._value .. max._value, name = "year")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			Year(value.toInt())
	}
}
