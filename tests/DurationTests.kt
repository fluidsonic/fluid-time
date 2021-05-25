import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


@ExperimentalTime
class DurationTests {

	@Test
	fun testFactory() {
		Duration(days = 1, hours = 2, minutes = 3, seconds = 4, nanoseconds = 5).toComponents { days, hours, minutes, seconds, nanoseconds ->
			assertEquals(expected = 1, actual = days)
			assertEquals(expected = 2, actual = hours)
			assertEquals(expected = 3, actual = minutes)
			assertEquals(expected = 4, actual = seconds)
			assertEquals(expected = 5, actual = nanoseconds)
		}
	}


	@Test
	fun testParse() {
		assertEquals(expected = assertFailsWith<IllegalArgumentException> { Duration.parse("") }.message, actual = "Invalid ISO-8601 duration: ")
		assertEquals(expected = Duration.ZERO, actual = Duration.parse("PT0S"))
	}


	@Test
	fun testParseOrNull() {
		assertEquals(
			expected = null,
			actual = Duration.parseOrNull("")
		)
		assertEquals(
			expected = null,
			actual = Duration.parseOrNull("PT")
		)
		assertEquals(
			expected = null,
			actual = Duration.parseOrNull("PT.S")
		)
		assertEquals(
			expected = Duration.ZERO,
			actual = Duration.parseOrNull("PT0S")
		)
		assertEquals(
			expected = Duration(days = 10),
			actual = Duration.parseOrNull("P10D")
		)
		assertEquals(
			expected = Duration(seconds = 10),
			actual = Duration.parseOrNull("PT10S")
		)
		assertEquals(
			expected = Duration(minutes = 10, seconds = 10),
			actual = Duration.parseOrNull("PT10M10S")
		)
		assertEquals(
			expected = Duration(hours = 10, minutes = 10, seconds = 10),
			actual = Duration.parseOrNull("PT10H10M10S")
		)
		assertEquals(
			expected = Duration(hours = 10, minutes = 10, seconds = 10, nanoseconds = 100_000_000),
			actual = Duration.parseOrNull("PT10H10M10.1S")
		)
		assertEquals(
			expected = Duration(days = 10, hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_456_789),
			actual = Duration.parseOrNull("P10DT10H10M10.123456789S")
		)
		assertEquals(
			expected = Duration(days = -10, hours = -10, minutes = -10, seconds = -10, nanoseconds = -123_456_789),
			actual = Duration.parseOrNull("-P10DT10H10M10.123456789S")
		)
		assertEquals(
			expected = Duration(days = -10, hours = -10, minutes = -10, seconds = -10, nanoseconds = -123_456_789),
			actual = Duration.parseOrNull("-p10dt10h10m10.123456789s")
		)
	}
}
