import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class LocalDateTimeTests {

	@Test
	fun testParseOrNull() {
		assertEquals(expected = LocalDateTime.parse("2019-10-01T18:43:15"), actual = LocalDateTime.parseOrNull("2019-10-01T18:43:15"))
		assertNull(Timestamp.parseOrNull("x"))
	}


	@Test
	fun testTime() {
		assertEquals(
			expected = LocalTime(1, 2, 3, 4),
			actual = LocalDateTime(2020, 12, 15, 1, 2, 3, 4).time
		)
	}


	@Test
	fun testToTimestamp() {
		assertEquals(
			expected = LocalDateTime(2020, 12, 15, 1, 2, 3, 4).toInstant(TimeZone.UTC),
			actual = LocalDateTime(2020, 12, 15, 1, 2, 3, 4).toTimestamp(TimeZone.UTC)
		)
	}
}
