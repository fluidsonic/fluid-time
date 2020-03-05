package tests

import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


object PrecisionDurationTest {

	@Test
	fun testArithmethic() {
		val twoSeconds = PreciseDuration.of(seconds = 2)

		assertEquals(PreciseDuration.of(seconds = 1, nanoseconds = 1), PreciseDuration.of(seconds = -1, nanoseconds = -1).absolute)
		assertEquals(PreciseDuration.of(seconds = 0, nanoseconds = 0), PreciseDuration.of(seconds = 0, nanoseconds = 0).absolute)
		assertEquals(PreciseDuration.of(seconds = 1, nanoseconds = 1), PreciseDuration.of(seconds = 1, nanoseconds = 1).absolute)

		assertNotEquals(PreciseDuration.of(seconds = 1, nanoseconds = 1), PreciseDuration.of(seconds = 1))
		assertNotEquals(PreciseDuration.of(seconds = 1, nanoseconds = 1), PreciseDuration.of(nanoseconds = 1))

		assertEquals(PreciseDuration.of(seconds = -2), PreciseDuration.zero - PreciseDuration.of(seconds = 2))
		assertSame(twoSeconds, twoSeconds - PreciseDuration.zero)
		assertSame(twoSeconds, twoSeconds.minus(seconds = 0))
		assertSame(twoSeconds, twoSeconds.minus(seconds = 0L))
		assertSame(twoSeconds, twoSeconds.minus(seconds = Seconds(0)))
		assertEquals(PreciseDuration.of(seconds = 2), PreciseDuration.of(seconds = 10) - PreciseDuration.of(seconds = 8))
		assertEquals(PreciseDuration.of(seconds = 1, nanoseconds = 999_999_999), PreciseDuration.of(seconds = 10) - PreciseDuration.of(seconds = 8, nanoseconds = 1))
		assertEquals(PreciseDuration.of(seconds = 2), PreciseDuration.of(seconds = 10).minus(seconds = 8))
		assertEquals(PreciseDuration.of(seconds = 1, nanoseconds = 999_999_999), PreciseDuration.of(seconds = 10).minus(seconds = 8, nanoseconds = 1))

		assertEquals(
			PreciseDuration.of(seconds = 82_738, nanoseconds = 998_998_999),
			PreciseDuration.of(days = 2).minus(
				days = 1,
				hours = 1,
				minutes = 1,
				seconds = 1,
				milliseconds = 1,
				microseconds = 1,
				nanoseconds = 1
			)
		)

		assertEquals(
			PreciseDuration.of(seconds = 82_738, nanoseconds = 998_998_999),
			PreciseDuration.of(days = 2).minus(
				days = 1L,
				hours = 1L,
				minutes = 1L,
				seconds = 1L,
				milliseconds = 1L,
				microseconds = 1L,
				nanoseconds = 1L
			)
		)

		assertEquals(
			PreciseDuration.of(seconds = 82_738, nanoseconds = 998_998_999),
			PreciseDuration.of(days = 2).minus(
				days = Days(1),
				hours = Hours(1),
				minutes = Minutes(1),
				seconds = Seconds(1),
				milliseconds = Milliseconds(1),
				microseconds = Microseconds(1),
				nanoseconds = Nanoseconds(1)
			)
		)

		assertSame(twoSeconds, PreciseDuration.zero + twoSeconds)
		assertSame(twoSeconds, twoSeconds + PreciseDuration.zero)
		assertSame(twoSeconds, twoSeconds.plus(seconds = 0))
		assertSame(twoSeconds, twoSeconds.plus(seconds = 0L))
		assertSame(twoSeconds, twoSeconds.plus(seconds = Seconds(0)))
		assertEquals(PreciseDuration.of(seconds = 18), PreciseDuration.of(seconds = 10) + PreciseDuration.of(seconds = 8))
		assertEquals(PreciseDuration.of(seconds = 18, nanoseconds = 1), PreciseDuration.of(seconds = 10) + PreciseDuration.of(seconds = 8, nanoseconds = 1))
		assertEquals(PreciseDuration.of(seconds = 18), PreciseDuration.of(seconds = 10).plus(seconds = 8))
		assertEquals(PreciseDuration.of(seconds = 18, nanoseconds = 1), PreciseDuration.of(seconds = 10).plus(seconds = 8, nanoseconds = 1))

		assertEquals(
			PreciseDuration.of(seconds = 262_861, nanoseconds = 1_001_001),
			PreciseDuration.of(days = 2).plus(
				days = 1,
				hours = 1,
				minutes = 1,
				seconds = 1,
				milliseconds = 1,
				microseconds = 1,
				nanoseconds = 1
			)
		)

		assertEquals(
			PreciseDuration.of(seconds = 262_861, nanoseconds = 1_001_001),
			PreciseDuration.of(days = 2).plus(
				days = 1L,
				hours = 1L,
				minutes = 1L,
				seconds = 1L,
				milliseconds = 1L,
				microseconds = 1L,
				nanoseconds = 1L
			)
		)

		assertEquals(
			PreciseDuration.of(seconds = 262_861, nanoseconds = 1_001_001),
			PreciseDuration.of(days = 2).plus(
				days = Days(1),
				hours = Hours(1),
				minutes = Minutes(1),
				seconds = Seconds(1),
				milliseconds = Milliseconds(1),
				microseconds = Microseconds(1),
				nanoseconds = Nanoseconds(1)
			)
		)

		assertEquals(PreciseDuration.of(seconds = 20, nanoseconds = 20), PreciseDuration.of(seconds = 10, nanoseconds = 10) * 2)
		assertEquals(PreciseDuration.of(seconds = 21), PreciseDuration.of(seconds = 10, nanoseconds = 500_000_000) * 2)
		assertEquals(PreciseDuration.of(seconds = 20, nanoseconds = 20), PreciseDuration.of(seconds = 10, nanoseconds = 10) * 2L)
		assertEquals(PreciseDuration.of(seconds = 21), PreciseDuration.of(seconds = 10, nanoseconds = 500_000_000) * 2L)
		assertSame(PreciseDuration.zero, twoSeconds * 0)
		assertSame(twoSeconds, twoSeconds * 1)
		assertEquals(PreciseDuration.of(seconds = -10, nanoseconds = -10), PreciseDuration.of(seconds = 10, nanoseconds = 10) * -1)
		assertEquals(PreciseDuration.of(seconds = -10, nanoseconds = -10), PreciseDuration.of(seconds = 10, nanoseconds = 10) * -1L)

		assertEquals(PreciseDuration.of(seconds = -10, nanoseconds = -10), -PreciseDuration.of(seconds = 10, nanoseconds = 10))

		assertEquals(PreciseDuration.of(seconds = 20, nanoseconds = 20), 2 * PreciseDuration.of(seconds = 10, nanoseconds = 10))
		assertEquals(PreciseDuration.of(seconds = 20, nanoseconds = 20), 2L * PreciseDuration.of(seconds = 10, nanoseconds = 10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(PreciseDuration.of(seconds = 1).compareTo(PreciseDuration.of(seconds = 2)) < 0)
		assertTrue(PreciseDuration.of(seconds = 1).compareTo(PreciseDuration.of(seconds = 1)) == 0)
		assertTrue(PreciseDuration.of(seconds = 2).compareTo(PreciseDuration.of(seconds = 1)) > 0)
		assertTrue(PreciseDuration.of(seconds = 1).compareTo(PreciseDuration.of(seconds = 1, nanoseconds = 1)) < 0)
		assertTrue(PreciseDuration.of(seconds = 1).compareTo(PreciseDuration.of(seconds = 1)) == 0)
		assertTrue(PreciseDuration.of(seconds = 1, nanoseconds = 1).compareTo(PreciseDuration.of(seconds = 1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Seconds.zero, PreciseDuration.zero.seconds)
		assertEquals(Nanoseconds.zero, PreciseDuration.zero.partialNanoseconds)
	}


	@Test
	fun testConstruction() {
		assertEquals(
			PreciseDuration.of(seconds = 90_061, nanoseconds = 1_001_001),
			PreciseDuration.of(
				days = 1,
				hours = 1,
				minutes = 1,
				seconds = 1,
				milliseconds = 1,
				microseconds = 1,
				nanoseconds = 1
			)
		)

		assertEquals(
			PreciseDuration.of(seconds = 90_061, nanoseconds = 1_001_001),
			PreciseDuration.of(
				days = 1L,
				hours = 1L,
				minutes = 1L,
				seconds = 1L,
				milliseconds = 1L,
				microseconds = 1L,
				nanoseconds = 1L
			)
		)

		assertEquals(
			PreciseDuration.of(seconds = 90_061, nanoseconds = 1_001_001),
			PreciseDuration.of(
				days = Days(1),
				hours = Hours(1),
				minutes = Minutes(1),
				seconds = Seconds(1),
				milliseconds = Milliseconds(1),
				microseconds = Microseconds(1),
				nanoseconds = Nanoseconds(1)
			)
		)
	}


	@Test
	@OptIn(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), PreciseDuration.of(seconds = 86_400, nanoseconds = 999_999_999).toDays())
		assertEquals(10.seconds, PreciseDuration.of(seconds = 10).toDuration())
		assertEquals(10_000_000_010.nanoseconds, PreciseDuration.of(seconds = 10, nanoseconds = 10).toDuration())
		assertEquals(Hours(1), PreciseDuration.of(seconds = 3_600).toHours())
		assertEquals(Microseconds(10_000_000), PreciseDuration.of(seconds = 10).toMicroseconds())
		assertEquals(Milliseconds(10_000), PreciseDuration.of(seconds = 10).toMilliseconds())
		assertEquals(Minutes(1), PreciseDuration.of(seconds = 60).toMinutes())
		assertEquals(Nanoseconds(10_000_000_000), PreciseDuration.of(seconds = 10).toNanoseconds())
		assertEquals(Seconds(10), PreciseDuration.of(seconds = 10, nanoseconds = 10).toSeconds())

		assertEquals("PT0S", PreciseDuration.zero.toString())
		assertEquals("PT10S", PreciseDuration.of(seconds = 10).toString())
		assertEquals("PT10M10S", PreciseDuration.of(minutes = 10, seconds = 10).toString())
		assertEquals("PT10H10M10S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10).toString())
		assertEquals("PT10H10M10.1S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 100_000_000).toString())
		assertEquals("PT10H10M10.12S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 120_000_000).toString())
		assertEquals("PT10H10M10.123S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_000_000).toString())
		assertEquals("PT10H10M10.1234S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_400_000).toString())
		assertEquals("PT10H10M10.12345S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_450_000).toString())
		assertEquals("PT10H10M10.123456S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_456_000).toString())
		assertEquals("PT10H10M10.1234567S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_456_700).toString())
		assertEquals("PT10H10M10.12345678S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_456_780).toString())
		assertEquals("PT10H10M10.123456789S", PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_456_789).toString())
		assertEquals("-PT10H10M10.123456789S", PreciseDuration.of(hours = -10, minutes = -10, seconds = -10, nanoseconds = -123_456_789).toString())

		assertEquals(PreciseDuration.of(seconds = 10, nanoseconds = 10), 10_000_000_010.nanoseconds.toPreciseDuration())
	}


	@Test
	fun testJson() = assertJsonSerialization(
		value = PreciseDuration.of(hours = 48, minutes = 12, seconds = 34, milliseconds = 567),
		json = """ "PT48H12M34.567S" """,
		serializer = PreciseDuration.serializer()
	)


	@Test
	fun testParse() {
		assertEquals(null, PreciseDuration.parse(""))
		assertEquals(null, PreciseDuration.parse("PT"))
		assertEquals(null, PreciseDuration.parse("PT.S"))
		assertEquals(PreciseDuration.zero, PreciseDuration.parse("PT0S"))
		assertEquals(PreciseDuration.of(days = 10), PreciseDuration.parse("P10D"))
		assertEquals(PreciseDuration.of(seconds = 10), PreciseDuration.parse("PT10S"))
		assertEquals(PreciseDuration.of(minutes = 10, seconds = 10), PreciseDuration.parse("PT10M10S"))
		assertEquals(PreciseDuration.of(hours = 10, minutes = 10, seconds = 10), PreciseDuration.parse("PT10H10M10S"))
		assertEquals(PreciseDuration.of(hours = 10, minutes = 10, seconds = 10, nanoseconds = 100_000_000), PreciseDuration.parse("PT10H10M10.1S"))
		assertEquals(PreciseDuration.of(days = 10, hours = 10, minutes = 10, seconds = 10, nanoseconds = 123_456_789), PreciseDuration.parse("P10DT10H10M10.123456789S"))
		assertEquals(PreciseDuration.of(days = -10, hours = -10, minutes = -10, seconds = -10, nanoseconds = -123_456_789), PreciseDuration.parse("-P10DT10H10M10.123456789S"))
		assertEquals(PreciseDuration.of(days = -10, hours = -10, minutes = -10, seconds = -10, nanoseconds = -123_456_789), PreciseDuration.parse("-p10dt10h10m10.123456789s"))
	}


	@Test
	fun testTests() {
		assertTrue(PreciseDuration.of(seconds = -1).isNegative)
		assertFalse(PreciseDuration.of(seconds = 0).isNegative)
		assertFalse(PreciseDuration.of(seconds = 1).isNegative)

		assertFalse(PreciseDuration.of(seconds = -1).isPositive)
		assertFalse(PreciseDuration.of(seconds = 0).isPositive)
		assertTrue(PreciseDuration.of(seconds = 1).isPositive)

		assertFalse(PreciseDuration.of(seconds = -1).isZero)
		assertTrue(PreciseDuration.of(seconds = 0).isZero)
		assertFalse(PreciseDuration.of(seconds = 1).isZero)
	}
}
