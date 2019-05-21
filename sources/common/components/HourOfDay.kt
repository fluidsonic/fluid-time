package com.github.fluidsonic.fluid.time


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class HourOfDay internal constructor(private val _value: Byte) : DateTimeComponent<HourOfDay, Hours> {

	override fun compareTo(other: HourOfDay) =
		_value.compareTo(other._value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Hours(other))


	override fun minus(other: HourOfDay) =
		Hours(value - other.value)


	override fun minus(other: Hours) =
		map { it - other.value }


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Hours(other))


	override fun plus(other: Hours) =
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

		val zero = unchecked(0)
		val max = unchecked(23)
		val min = zero


		fun of(value: Long): HourOfDay {
			check(value, inRange = min._value .. max._value, name = "hour [of day]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			HourOfDay(value.toByte())
	}
}
