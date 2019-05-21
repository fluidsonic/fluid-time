package com.github.fluidsonic.fluid.time


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class MinuteOfHour internal constructor(private val _value: Byte) : DateTimeComponent<MinuteOfHour, Minutes> {

	override fun compareTo(other: MinuteOfHour) =
		_value.compareTo(other._value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Minutes(other))


	override fun minus(other: MinuteOfHour) =
		Minutes(value - other.value)


	override fun minus(other: Minutes) =
		map { it - other.value }


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Minutes(other))


	override fun plus(other: Minutes) =
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
		val max = unchecked(59)
		val min = zero


		fun of(value: Long): MinuteOfHour {
			check(value, inRange = min._value .. max._value, name = "minute [of hour]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			MinuteOfHour(value.toByte())
	}
}
