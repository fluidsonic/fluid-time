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
		DayOfMonth.unchecked(daysIn(year).value)


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Months(other))


	override fun minus(other: MonthOfYear) =
		Months(value - other.value) // FIXME


	override fun minus(other: Months) =
		plus(-(other % 12))


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Months(other))


	override fun plus(other: Months) =
		unchecked((value + (other.value % 12) + 12) % 12)


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toString() =
		name


	val value: Long
		get() = ordinal + 1L


	companion object {

		val max = december
		val min = january


		fun of(value: Long): MonthOfYear {
			check(value, inRange = min.value .. max.value, name = "month [of year]")

			return unchecked(value)
		}


		internal fun unchecked(value: Long) =
			values()[(value - 1).toInt()]
	}
}
