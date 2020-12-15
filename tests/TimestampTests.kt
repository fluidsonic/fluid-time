import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class TimestampTests {

	@Test
	fun testInstantIsTimestamp() {
		@Suppress("USELESS_CAST")
		assertNotNull(Clock.System.now() as Timestamp)
	}


	@Test
	fun testParseOrNull() {
		assertEquals(expected = Timestamp.parse("1970-01-01T00:00:00Z"), actual = Timestamp.parseOrNull("1970-01-01T00:00:00Z"))
		assertNull(Timestamp.parseOrNull("x"))
	}


	@Test
	fun testToLocalDate() {
		assertEquals(
			expected = LocalDate(1970, 1, 1),
			actual = Instant.fromEpochSeconds(0).toLocalDate(TimeZone.UTC)
		)
	}


	@Test
	fun testToLocalTime() {
		assertEquals(
			expected = LocalTime(0, 0),
			actual = Instant.fromEpochSeconds(0).toLocalTime(TimeZone.UTC)
		)
	}
}
