package tests

import io.fluidsonic.locale.*
import io.fluidsonic.time.*
import kotlin.test.*


class DayOfWeekTest {

	private val locale = Locale.parseOrNull("en-US")!!


	@Test
	fun testDisplayNameCharacter() {
		assertEquals(expected = "M", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.character))
		assertEquals(expected = "T", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.character))
		assertEquals(expected = "W", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.character))
		assertEquals(expected = "T", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.character))
		assertEquals(expected = "F", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.character))
		assertEquals(expected = "S", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.character))
		assertEquals(expected = "S", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.character))

		assertEquals(expected = "M", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
		assertEquals(expected = "T", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
		assertEquals(expected = "W", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
		assertEquals(expected = "T", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
		assertEquals(expected = "F", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
		assertEquals(expected = "S", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
		assertEquals(expected = "S", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.characterStandalone))
	}


	@Test
	fun testDisplayNameShort() {
		assertEquals(expected = "Mo", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.short))
		assertEquals(expected = "Tu", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.short))
		assertEquals(expected = "We", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.short))
		assertEquals(expected = "Th", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.short))
		assertEquals(expected = "Fr", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.short))
		assertEquals(expected = "Sa", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.short))
		assertEquals(expected = "Su", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.short))

		assertEquals(expected = "Mo", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
		assertEquals(expected = "Tu", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
		assertEquals(expected = "We", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
		assertEquals(expected = "Th", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
		assertEquals(expected = "Fr", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
		assertEquals(expected = "Sa", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
		assertEquals(expected = "Su", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.shortStandalone))
	}


	@Test
	fun testDisplayNameMedium() {
		assertEquals(expected = "Mon", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.medium))
		assertEquals(expected = "Tue", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.medium))
		assertEquals(expected = "Wed", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.medium))
		assertEquals(expected = "Thu", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.medium))
		assertEquals(expected = "Fri", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.medium))
		assertEquals(expected = "Sat", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.medium))
		assertEquals(expected = "Sun", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.medium))

		assertEquals(expected = "Mon", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
		assertEquals(expected = "Tue", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
		assertEquals(expected = "Wed", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
		assertEquals(expected = "Thu", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
		assertEquals(expected = "Fri", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
		assertEquals(expected = "Sat", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
		assertEquals(expected = "Sun", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.mediumStandalone))
	}


	@Test
	fun testDisplayNameFull() {
		assertEquals(expected = "Monday", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.full))
		assertEquals(expected = "Tuesday", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.full))
		assertEquals(expected = "Wednesday", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.full))
		assertEquals(expected = "Thursday", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.full))
		assertEquals(expected = "Friday", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.full))
		assertEquals(expected = "Saturday", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.full))
		assertEquals(expected = "Sunday", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.full))

		assertEquals(expected = "Monday", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
		assertEquals(expected = "Tuesday", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
		assertEquals(expected = "Wednesday", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
		assertEquals(expected = "Thursday", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
		assertEquals(expected = "Friday", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
		assertEquals(expected = "Saturday", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
		assertEquals(expected = "Sunday", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeek.Format.fullStandalone))
	}


	@Test
	fun testJson() =
		mapOf(
			DayOfWeek.monday to "monday",
			DayOfWeek.tuesday to "tuesday",
			DayOfWeek.wednesday to "wednesday",
			DayOfWeek.thursday to "thursday",
			DayOfWeek.friday to "friday",
			DayOfWeek.saturday to "saturday",
			DayOfWeek.sunday to "sunday"
		).forEach { (value, string) ->
			assertJsonSerialization(
				value = value,
				json = """ "$string" """,
				serializer = DayOfWeek.serializer()
			)
		}


	@Test
	fun testMinus() {
		assertEquals(DayOfWeek.monday, DayOfWeek.tuesday - Days(1))
		assertEquals(DayOfWeek.tuesday, DayOfWeek.wednesday - Days(1))
		assertEquals(DayOfWeek.wednesday, DayOfWeek.thursday - Days(1))
		assertEquals(DayOfWeek.thursday, DayOfWeek.friday - Days(1))
		assertEquals(DayOfWeek.friday, DayOfWeek.saturday - Days(1))
		assertEquals(DayOfWeek.saturday, DayOfWeek.sunday - Days(1))
		assertEquals(DayOfWeek.sunday, DayOfWeek.monday - Days(1))

		assertEquals(DayOfWeek.monday, DayOfWeek.tuesday - Days(8))
		assertEquals(DayOfWeek.tuesday, DayOfWeek.wednesday - Days(8))
		assertEquals(DayOfWeek.wednesday, DayOfWeek.thursday - Days(8))
		assertEquals(DayOfWeek.thursday, DayOfWeek.friday - Days(8))
		assertEquals(DayOfWeek.friday, DayOfWeek.saturday - Days(8))
		assertEquals(DayOfWeek.saturday, DayOfWeek.sunday - Days(8))
		assertEquals(DayOfWeek.sunday, DayOfWeek.monday - Days(8))
	}


	@Test
	fun testPlus() {
		assertEquals(DayOfWeek.monday, DayOfWeek.sunday + Days(1))
		assertEquals(DayOfWeek.tuesday, DayOfWeek.monday + Days(1))
		assertEquals(DayOfWeek.wednesday, DayOfWeek.tuesday + Days(1))
		assertEquals(DayOfWeek.thursday, DayOfWeek.wednesday + Days(1))
		assertEquals(DayOfWeek.friday, DayOfWeek.thursday + Days(1))
		assertEquals(DayOfWeek.saturday, DayOfWeek.friday + Days(1))
		assertEquals(DayOfWeek.sunday, DayOfWeek.saturday + Days(1))

		assertEquals(DayOfWeek.monday, DayOfWeek.sunday + Days(8))
		assertEquals(DayOfWeek.tuesday, DayOfWeek.monday + Days(8))
		assertEquals(DayOfWeek.wednesday, DayOfWeek.tuesday + Days(8))
		assertEquals(DayOfWeek.thursday, DayOfWeek.wednesday + Days(8))
		assertEquals(DayOfWeek.friday, DayOfWeek.thursday + Days(8))
		assertEquals(DayOfWeek.saturday, DayOfWeek.friday + Days(8))
		assertEquals(DayOfWeek.sunday, DayOfWeek.saturday + Days(8))
	}
}
