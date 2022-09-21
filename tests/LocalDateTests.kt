import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class LocalDateTests {

	@Test
	fun testFactoryOrNull() {
		assertNull(LocalDateOrNull(Int.MIN_VALUE, 1, 1))
		assertNull(LocalDateOrNull(1, -1, 1))
		assertNull(LocalDateOrNull(1, 1, -1))
		assertNull(LocalDateOrNull(Int.MAX_VALUE, 1, 1))
		assertNull(LocalDateOrNull(1, 13, 1))
		assertNull(LocalDateOrNull(1, 1, 32))
		assertNotNull(LocalDateOrNull(1, 1, 1))

		assertNull(LocalDateOrNull(Int.MIN_VALUE, Month.JANUARY, 1))
		assertNull(LocalDateOrNull(1, Month.JANUARY, -1))
		assertNull(LocalDateOrNull(Int.MAX_VALUE, Month.JANUARY, 1))
		assertNull(LocalDateOrNull(1, Month.JANUARY, 32))
		assertNotNull(LocalDateOrNull(1, Month.JANUARY, 1))
	}


	@Test
	fun testParseOrNull() {
		assertEquals(expected = LocalDate.parse("2019-10-01"), actual = LocalDate.parseOrNull("2019-10-01"))
		assertNull(Timestamp.parseOrNull("x"))
	}
}
