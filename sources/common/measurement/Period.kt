package io.fluidsonic.time


// TODO add more functionality
// TODO add serializer
class Period internal constructor(
	val years: Years,
	val months: Months,
	val days: Days,
	@Suppress("UNUSED_PARAMETER") disambiguation: Unit
) {

	init {
		freeze()
	}


	override fun equals(other: Any?) =
		this === other || (
			other is Period
				&& days == other.days
				&& months == other.months
				&& years == other.years
			)


	override fun hashCode() =
		days.hashCode() xor months.hashCode() xor years.hashCode()


	override fun toString() =
		when {
			this === zero -> "P0D"
			else -> buildString {
				append('P')

				if (years.value != 0L)
					append(years.value).append('Y')
				if (months.value != 0L)
					append(months.value).append('M')
				if (days.value != 0L)
					append(days.value).append('D')
			}
		}


	companion object {

		val zero = Period(years = Years.zero, months = Months.zero, days = Days.zero, disambiguation = Unit)
	}
}


@Suppress("FunctionName", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@kotlin.internal.InlineOnly
inline fun Period(
	years: Int = 0,
	months: Int = 0,
	days: Int = 0
): Period =
	Period(
		years = years.toLong(),
		months = months.toLong(),
		days = days.toLong()
	)


@Suppress("FunctionName", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@kotlin.internal.InlineOnly
inline fun Period(
	years: Long = 0,
	months: Long = 0,
	days: Long = 0
): Period =
	Period(
		years = Years(years),
		months = Months(months),
		days = Days(days)
	)


@Suppress("FunctionName")
fun Period(
	years: Years = Years.zero,
	months: Months = Months.zero,
	days: Days = Days.zero
): Period =
	if ((years.value or months.value or days.value) == 0L)
		Period.zero
	else
		Period(years = years, months = months, days = days, disambiguation = Unit)


internal expect fun Period.Companion.between(startInclusive: LocalDate, endExclusive: LocalDate): Period
