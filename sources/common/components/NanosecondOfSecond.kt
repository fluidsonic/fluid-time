package com.github.fluidsonic.fluid.time


// TODO check for overflows
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class NanosecondOfSecond internal constructor(private val _value: Int) : DateTimeComponent<NanosecondOfSecond, Nanoseconds> {

	override fun compareTo(other: NanosecondOfSecond) =
		_value.compareTo(other._value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Nanoseconds(other))


	override fun minus(other: NanosecondOfSecond) =
		Nanoseconds(value - other.value)


	override fun minus(other: Nanoseconds) =
		map { it - other.value }


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Nanoseconds(other))


	override fun plus(other: Nanoseconds) =
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

		val zero = unchecked(0)
		val max = unchecked(999_999_999)
		val min = zero


		fun of(value: Long): NanosecondOfSecond {
			check(value, inRange = min.value .. max.value, name = "nanosecond [of second]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			NanosecondOfSecond(value.toInt())
	}
}
