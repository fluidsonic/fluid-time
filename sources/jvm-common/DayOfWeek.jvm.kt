package io.fluidsonic.time

import io.fluidsonic.locale.*


public fun DayOfWeek.toPlatform(): PlatformDayOfWeek = when (this) {
	DayOfWeek.monday -> PlatformDayOfWeek.MONDAY
	DayOfWeek.tuesday -> PlatformDayOfWeek.TUESDAY
	DayOfWeek.wednesday -> PlatformDayOfWeek.WEDNESDAY
	DayOfWeek.thursday -> PlatformDayOfWeek.THURSDAY
	DayOfWeek.friday -> PlatformDayOfWeek.FRIDAY
	DayOfWeek.saturday -> PlatformDayOfWeek.SATURDAY
	DayOfWeek.sunday -> PlatformDayOfWeek.SUNDAY
}


public fun PlatformDayOfWeek.toCommon(): DayOfWeek = when (this) {
	PlatformDayOfWeek.MONDAY -> DayOfWeek.monday
	PlatformDayOfWeek.TUESDAY -> DayOfWeek.tuesday
	PlatformDayOfWeek.WEDNESDAY -> DayOfWeek.wednesday
	PlatformDayOfWeek.THURSDAY -> DayOfWeek.thursday
	PlatformDayOfWeek.FRIDAY -> DayOfWeek.friday
	PlatformDayOfWeek.SATURDAY -> DayOfWeek.saturday
	PlatformDayOfWeek.SUNDAY -> DayOfWeek.sunday
}


internal actual fun DayOfWeek_displayName(dayOfWeek: DayOfWeek, locale: Locale, format: DayOfWeek.Format): String {
	val textStyle = when (format) {
		DayOfWeek.Format.character -> PlatformTextStyle.NARROW
		DayOfWeek.Format.characterStandalone ->
			if (System.getProperty("java.version").orEmpty().startsWith("1.8.")) // NARROW_STANDALONE is broken in Java 8
				PlatformTextStyle.NARROW
			else
				PlatformTextStyle.NARROW_STANDALONE
		DayOfWeek.Format.full -> PlatformTextStyle.FULL
		DayOfWeek.Format.fullStandalone -> PlatformTextStyle.FULL_STANDALONE
		DayOfWeek.Format.medium -> PlatformTextStyle.SHORT
		DayOfWeek.Format.mediumStandalone -> PlatformTextStyle.SHORT_STANDALONE
		DayOfWeek.Format.short -> PlatformTextStyle.SHORT
		DayOfWeek.Format.shortStandalone -> PlatformTextStyle.SHORT_STANDALONE
	}

	val platformLocale = locale.toPlatform()
	val name = dayOfWeek.toPlatform().getDisplayName(textStyle, platformLocale)!!

	return when (format) {
		DayOfWeek.Format.short, DayOfWeek.Format.shortStandalone ->
			if (platformLocale.language == "en")
				name.take(2)
			else
				name
		else ->
			name
	}
}
