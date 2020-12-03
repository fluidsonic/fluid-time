@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class Year @PublishedApi internal constructor(@PublishedApi internal val value: Int) : DateTimeComponent<Year, Years> {

	override inline fun compareTo(other: Year): Int =
		value.compareTo(other.value)


	public val isLeap: Boolean
		get() = value % 4 == 0 && (value % 100 != 0 || value % 400 == 0)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long): Year =
		of(transform(toLong()))


	override inline operator fun minus(other: Year): Years =
		Years(toLong() - other.toLong())


	override inline operator fun minus(other: Years): Year =
		map { it - other.toLong() }


	override inline operator fun plus(other: Years): Year =
		map { it + other.toLong() }


	override inline fun toInt(): Int =
		value


	override inline fun toLong(): Long =
		value.toLong()


	override inline fun toString(): String =
		value.toString()


	public companion object : DateTimeComponent.CompanionInterface<Year> {

		/* override */ public val max: Year = unchecked(999_999_999)
		/* override */ public val min: Year = unchecked(-999_999_999)


		override inline fun isValid(value: Long): Boolean =
			value in min.toLong() .. max.toLong()


		override inline fun of(value: Long): Year {
			require(isValid(value)) { "Year must be in range $min .. $max: $value" }

			return unchecked(value)
		}


		override inline fun ofOrNull(value: Long): Year? {
			if (!isValid(value))
				return null

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long): Year =
			Year(value.toInt())
	}
}
