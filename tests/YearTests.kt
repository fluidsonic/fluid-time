import io.fluidsonic.time.*
import kotlin.test.*


class YearTests {

	@Test
	fun testIsLeap() {
		val leapYears = listOf(
			2000, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048,
			2052, 2056, 2060, 2064, 2068, 2072, 2076, 2080, 2084, 2088, 2092, 2096, 2104,
		)

		for (year in 1999 .. 2107)
			if (leapYears.contains(year))
				assertTrue(Year.isLeap(year), message = "Expected $year to be a leap year but it isn't.")
			else
				assertFalse(Year.isLeap(year), message = "Expected $year to not be a leap year but it is.")
	}
}
