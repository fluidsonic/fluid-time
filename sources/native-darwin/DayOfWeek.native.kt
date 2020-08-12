package io.fluidsonic.time

import io.fluidsonic.locale.*
import platform.Foundation.*


internal actual fun DayOfWeek_displayName(dayOfWeek: DayOfWeek, locale: Locale, format: DayOfWeek.Format): String {
	val platformLocale = locale.toPlatform()

	val formatter = NSDateFormatter()
	formatter.locale = platformLocale

	@Suppress("UNCHECKED_CAST")
	val names = when (format) {
		DayOfWeek.Format.character -> formatter.veryShortWeekdaySymbols
		DayOfWeek.Format.characterStandalone -> formatter.veryShortStandaloneWeekdaySymbols
		DayOfWeek.Format.full -> formatter.weekdaySymbols
		DayOfWeek.Format.fullStandalone -> formatter.standaloneWeekdaySymbols
		DayOfWeek.Format.medium -> formatter.shortWeekdaySymbols
		DayOfWeek.Format.mediumStandalone -> formatter.shortStandaloneWeekdaySymbols
		DayOfWeek.Format.short -> formatter.shortWeekdaySymbols
		DayOfWeek.Format.shortStandalone -> formatter.shortStandaloneWeekdaySymbols
	} as List<String>

	val name = names[when (dayOfWeek) {
		DayOfWeek.monday -> 1
		DayOfWeek.tuesday -> 2
		DayOfWeek.wednesday -> 3
		DayOfWeek.thursday -> 4
		DayOfWeek.friday -> 5
		DayOfWeek.saturday -> 6
		DayOfWeek.sunday -> 0
	}]

	return when (format) {
		DayOfWeek.Format.short, DayOfWeek.Format.shortStandalone ->
			if (platformLocale.languageCode == "en")
				name.take(2)
			else
				name
		else ->
			name
	}
}
