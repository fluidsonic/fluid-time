import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class LocalTimeTest {

	@Test
	fun testConstant() {
		assertEquals(expected = LocalTime(23, 59, 59, 999_999_999), actual = LocalTime.max)
		assertEquals(expected = LocalTime(0, 0), actual = LocalTime.midnight)
		assertEquals(expected = LocalTime(0, 0), actual = LocalTime.min)
	}


	@Test
	fun testParseOrNull() {
		assertEquals(expected = LocalTime.parse("18:43:15"), actual = LocalTime.parseOrNull("18:43:15"))
		assertNull(Timestamp.parseOrNull("x"))
	}
}
