@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time


public enum class MonthOfYear : DateTimeComponent<MonthOfYear, Months> {

	january,
	february,
	march,
	april,
	may,
	june,
	july,
	august,
	september,
	october,
	november,
	december;


	public fun daysIn(year: Year): Days =
		Days(when (this) {
			january -> 31
			february -> if (year.isLeap) 29 else 28
			march -> 31
			april -> 30
			may -> 31
			june -> 30
			july -> 31
			august -> 31
			september -> 30
			october -> 31
			november -> 30
			december -> 31
		})


	override inline fun map(transform: (Long) -> Long): MonthOfYear =
		of(transform(toLong()))


	override inline operator fun minus(other: MonthOfYear): Months =
		Months(toLong() - other.toLong()) // FIXME check


	override inline operator fun minus(other: Months): MonthOfYear =
		plus(-(other % 12))


	override inline operator fun plus(other: Months): MonthOfYear =
		unchecked((toLong() + (other.toLong() % 12) + 12) % 12)


	override fun toInt(): Int =
		ordinal + 1


	override fun toLong(): Long =
		ordinal + 1L


	override fun toString(): String =
		name


	public companion object : DateTimeComponent.CompanionInterface<MonthOfYear> {

		/* override */ public val max: MonthOfYear = december
		/* override */ public val min: MonthOfYear = january


		override inline fun isValid(value: Long): Boolean =
			value in min.toLong() .. max.toLong()


		override inline fun of(value: Long): MonthOfYear {
			require(isValid(value)) { "Month of year must be in range ${min.toLong()} .. ${max.toLong()}: $value" }

			return unchecked(value)
		}


		override inline fun ofOrNull(value: Long): MonthOfYear? {
			if (!isValid(value))
				return null

			return unchecked(value)
		}


		@PublishedApi
		internal inline fun unchecked(value: Long): MonthOfYear =
			values()[(value - 1).toInt()]
	}
}
