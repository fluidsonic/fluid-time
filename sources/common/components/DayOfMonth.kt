@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class DayOfMonth @PublishedApi internal constructor(@PublishedApi internal val value: Byte) : DateTimeComponent<DayOfMonth, Days> {

	override inline fun compareTo(other: DayOfMonth): Int =
		value.compareTo(other.value)


	public fun isValidIn(month: MonthOfYear, year: Year): Boolean =
		this <= lastIn(month, year)


	override inline fun map(transform: (Long) -> Long): DayOfMonth =
		of(transform(toLong()))


	override inline operator fun minus(other: DayOfMonth): Days =
		Days(toLong() - other.toLong())


	override inline operator fun minus(other: Days): DayOfMonth =
		map { it - other.toLong() }


	override inline operator fun plus(other: Days): DayOfMonth =
		map { it + other.toLong() }


	override inline fun toInt(): Int =
		value.toInt()


	override inline fun toLong(): Long =
		value.toLong()


	override inline fun toString(): String =
		value.toString()


	public companion object : DateTimeComponent.CompanionInterface<DayOfMonth> {

		/* override */ public val max: DayOfMonth = unchecked(31)
		/* override */ public val min: DayOfMonth = unchecked(1)


		override inline fun isValid(value: Long): Boolean =
			value in min.toLong() .. max.toLong()


		public fun lastIn(month: MonthOfYear, year: Year): DayOfMonth =
			unchecked(month.daysIn(year).toLong())


		override inline fun of(value: Long): DayOfMonth {
			require(isValid(value)) { "Day of month must be in range $min .. $max: $value" }

			return unchecked(value)
		}


		override inline fun ofOrNull(value: Long): DayOfMonth? {
			if (!isValid(value))
				return null

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long): DayOfMonth =
			DayOfMonth(value.toByte())
	}
}
