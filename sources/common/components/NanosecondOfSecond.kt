package com.github.fluidsonic.fluid.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class NanosecondOfSecond internal constructor(private val value: Int) : DateTimeComponent<NanosecondOfSecond, Nanoseconds> {

	override fun compareTo(other: NanosecondOfSecond) =
		value.compareTo(other.value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: NanosecondOfSecond) =
		Nanoseconds(toLong() - other.toLong())


	override fun minus(other: Nanoseconds) =
		map { it - other.toLong() }


	override fun plus(other: Nanoseconds) =
		map { it + other.toLong() }


	override fun toInt() =
		value


	override fun toLong() =
		value.toLong()


	override fun toString() =
		value.toString()


	companion object : DateTimeComponent.CompanionInterface<NanosecondOfSecond> {

		override val max = unchecked(999_999_999)
		override val min = unchecked(0)


		override fun of(value: Long): NanosecondOfSecond {
			check(value, inRange = min.toLong() .. max.toLong(), name = "nanosecond [of second]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			NanosecondOfSecond(value.toInt())
	}
}
