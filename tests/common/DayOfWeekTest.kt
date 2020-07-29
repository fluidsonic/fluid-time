package tests

import io.fluidsonic.time.*
import kotlin.test.*


class DayOfWeekTest {

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
