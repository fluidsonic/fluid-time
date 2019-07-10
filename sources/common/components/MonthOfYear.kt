package com.github.fluidsonic.fluid.time


enum class MonthOfYear : DateTimeComponent<MonthOfYear, Months> {

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


	fun daysIn(year: Year) =
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


	fun lastDayIn(year: Year) =
		DayOfMonth.unchecked(daysIn(year).toLong())


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: MonthOfYear) =
		Months(toLong() - other.toLong()) // FIXME check


	override fun minus(other: Months) =
		plus(-(other % 12))


	override fun plus(other: Months) =
		unchecked((toLong() + (other.toLong() % 12) + 12) % 12)


	override fun toInt() =
		ordinal + 1


	override fun toLong() =
		ordinal + 1L


	override fun toString() =
		name


	companion object : DateTimeComponent.CompanionInterface<MonthOfYear> {

		override val max = december
		override val min = january


		override fun of(value: Long): MonthOfYear {
			check(value, inRange = min.toLong() .. max.toLong(), name = "month [of year]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			values()[(value - 1).toInt()]
	}
}
