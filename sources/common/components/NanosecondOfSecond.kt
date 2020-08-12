@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class NanosecondOfSecond @PublishedApi internal constructor(@PublishedApi internal val value: Int) : DateTimeComponent<NanosecondOfSecond, Nanoseconds> {

	override inline fun compareTo(other: NanosecondOfSecond): Int =
		value.compareTo(other.value)


	override inline fun map(transform: (Long) -> Long): NanosecondOfSecond =
		of(transform(toLong()))


	override inline operator fun minus(other: NanosecondOfSecond): Nanoseconds =
		Nanoseconds(toLong() - other.toLong())


	override inline operator fun minus(other: Nanoseconds): NanosecondOfSecond =
		map { it - other.toLong() }


	override inline operator fun plus(other: Nanoseconds): NanosecondOfSecond =
		map { it + other.toLong() }


	override inline fun toInt(): Int =
		value


	override inline fun toLong(): Long =
		value.toLong()


	override inline fun toString(): String =
		value.toString()


	public companion object : DateTimeComponent.CompanionInterface<NanosecondOfSecond> {

		/* override */ public val max: NanosecondOfSecond = unchecked(999_999_999)
		/* override */ public val min: NanosecondOfSecond = unchecked(0)


		override inline fun isValid(value: Long): Boolean =
			value in min.toLong() .. max.toLong()


		override inline fun of(value: Long): NanosecondOfSecond {
			require(isValid(value)) { "Nanosecond of second must be in range $min .. $max: $value" }

			return unchecked(value)
		}


		override inline fun ofOrNull(value: Long): NanosecondOfSecond? {
			if (!isValid(value))
				return null

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long) =
			NanosecondOfSecond(value.toInt())
	}
}
