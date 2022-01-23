import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*
import kotlin.time.Duration.Companion.seconds
import kotlinx.datetime.*


@OptIn(ExperimentalTime::class)
class TimestampTests {

	@Test
	fun testInstantIsTimestamp() {
		@Suppress("USELESS_CAST")
		assertNotNull(Clock.System.now() as Timestamp)
	}


	@Test
	fun testDurationSince() {
		assertEquals(
			expected = 1.seconds,
			actual = Timestamp.fromEpochSeconds(1).durationSince(Timestamp.fromEpochSeconds(0)),
		)
	}


	@Test
	fun testDurationUntil() {
		assertEquals(
			expected = 1.seconds,
			actual = Timestamp.fromEpochSeconds(0).durationUntil(Timestamp.fromEpochSeconds(1)),
		)
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
