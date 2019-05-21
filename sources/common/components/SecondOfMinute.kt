package com.github.fluidsonic.fluid.time


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class SecondOfMinute internal constructor(private val _value: Byte) : DateTimeComponent<SecondOfMinute, Seconds> {

	override fun compareTo(other: SecondOfMinute) =
		_value.compareTo(other._value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Seconds(other))


	override fun minus(other: SecondOfMinute) =
		Seconds(value - other.value)


	override fun minus(other: Seconds) =
		map { it - other.value }


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Seconds(other))


	override fun plus(other: Seconds) =
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


		fun of(value: Long): SecondOfMinute {
			check(value, inRange = min._value .. max._value, name = "second [of minute]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			SecondOfMinute(value.toByte())
	}
}
