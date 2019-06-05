package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*


// Because we still rely on platform implementation for converting between Timestamp and Local(Date)(Time) we lose some precision in the
// nanoseconds on some platforms. Thus for now the tests must be set up in a way which accounts for that.
object TimestampTest {

	@Test
	fun testCreateionWithMillisecondsSince1970() {
		assertEquals(
			expected = Timestamp.firstIn1970 + Hours(12),
			actual = Timestamp.of(millisecondsSince1970 = Milliseconds(12 * 60 * 60 * 1000))
		)
	}


	@Test
	fun testCreateionWithSecondsSince1970() {
		assertEquals(
			expected = Timestamp.firstIn1970 + Hours(12),
			actual = Timestamp.of(secondsSince1970 = Seconds(12 * 60 * 60))
		)
	}


	@Test
	fun testMillisecondsSince1970() {
		assertEquals(
			expected = Milliseconds(12 * 60 * 60 * 1000),
			actual = (Timestamp.firstIn1970 + Hours(12)).millisecondsSince1970
		)
	}


	@Test
	fun testNow() {
		val now = Timestamp.now()
		assertTrue(now.secondsSince1970 > Seconds(1_559_729_584L), "now ($now) is in the past") // Jun 5th 2019
		assertTrue(now.secondsSince1970 < Seconds(1_924_992_000L), "now ($now) is too far in the future") // Jan 1st 2031
	}


	@Test
	fun testSecondsSince1970() {
		assertEquals(
			expected = Seconds(12 * 60 * 60),
			actual = (Timestamp.firstIn1970 + Hours(12)).secondsSince1970
		)
	}


	@Test
	fun testToLocalDateTime() {
		assertEquals(
			expected = LocalDate.firstIn1970.atTime(0, 0, 0, 500_000_000),
			actual = (Timestamp.firstIn1970 + Milliseconds(500)).toLocalDateTime(TimeZone.utc)
		)
	}


	@Test
	fun testJson() = assertJsonSerialization(
		value = Timestamp.firstIn1970 + Milliseconds(500),
		json = """ "1970-01-01T00:00:00.500Z" """,
		serializer = Timestamp.serializer()
	)
}
