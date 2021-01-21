import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class MonthTests {

	@Test
	fun testDaysIn() {
		assertEquals(expected = 31, actual = Month.JANUARY.daysIn(2000))
		assertEquals(expected = 29, actual = Month.FEBRUARY.daysIn(2000))
		assertEquals(expected = 28, actual = Month.FEBRUARY.daysIn(2001))
		assertEquals(expected = 31, actual = Month.MARCH.daysIn(2000))
		assertEquals(expected = 30, actual = Month.APRIL.daysIn(2000))
		assertEquals(expected = 31, actual = Month.MAY.daysIn(2000))
		assertEquals(expected = 30, actual = Month.JUNE.daysIn(2000))
		assertEquals(expected = 31, actual = Month.JULY.daysIn(2000))
		assertEquals(expected = 31, actual = Month.AUGUST.daysIn(2000))
		assertEquals(expected = 30, actual = Month.SEPTEMBER.daysIn(2000))
		assertEquals(expected = 31, actual = Month.OCTOBER.daysIn(2000))
		assertEquals(expected = 30, actual = Month.NOVEMBER.daysIn(2000))
		assertEquals(expected = 31, actual = Month.DECEMBER.daysIn(2000))
	}
}
