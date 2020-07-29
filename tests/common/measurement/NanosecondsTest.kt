package tests

import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


class NanosecondsTest {

	@Test
	fun testArithmethic() {
		assertEquals(Nanoseconds(1), Nanoseconds(-1).absolute)
		assertEquals(Nanoseconds(0), Nanoseconds(0).absolute)
		assertEquals(Nanoseconds(1), Nanoseconds(1).absolute)

		assertEquals(Nanoseconds(5), Nanoseconds(10) / 2)
		assertEquals(Nanoseconds(5), Nanoseconds(10L) / 2)
		assertEquals(5, Nanoseconds(10) / Nanoseconds(2))

		assertEquals(Nanoseconds(2), Nanoseconds(10) - Nanoseconds(8))

		assertEquals(Nanoseconds(18), Nanoseconds(10) + Nanoseconds(8))

		assertEquals(Nanoseconds(1), Nanoseconds(10) % 3)
		assertEquals(Nanoseconds(1), Nanoseconds(10) % 3L)
		assertEquals(Nanoseconds(1), Nanoseconds(10) % Nanoseconds(3))

		assertEquals(Nanoseconds(20), Nanoseconds(10) * 2)
		assertEquals(Nanoseconds(20), Nanoseconds(10) * 2L)

		assertEquals(Nanoseconds(-10), -Nanoseconds(10))

		assertEquals(Nanoseconds(20), 2 * Nanoseconds(10))
		assertEquals(Nanoseconds(20), 2L * Nanoseconds(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Nanoseconds(1).compareTo(Nanoseconds(2)) < 0)
		assertTrue(Nanoseconds(1).compareTo(Nanoseconds(1)) == 0)
		assertTrue(Nanoseconds(2).compareTo(Nanoseconds(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Nanoseconds(Long.MAX_VALUE), Nanoseconds.max)
		assertEquals(Nanoseconds(Long.MIN_VALUE), Nanoseconds.min)
		assertEquals(Nanoseconds(86_400_000_000_000), Nanoseconds.perDay)
		assertEquals(Nanoseconds(3_600_000_000_000), Nanoseconds.perHour)
		assertEquals(Nanoseconds(1_000), Nanoseconds.perMicrosecond)
		assertEquals(Nanoseconds(1_000_000), Nanoseconds.perMillisecond)
		assertEquals(Nanoseconds(60_000_000_000), Nanoseconds.perMinute)
		assertEquals(Nanoseconds(1_000_000_000), Nanoseconds.perSecond)
		assertEquals(Nanoseconds(0), Nanoseconds.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Nanoseconds(123).toInt())
		assertEquals(123L, Nanoseconds(123L).toLong())
	}


	@Test
	@OptIn(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), Nanoseconds(86_400_000_000_000).toDays())
		assertEquals(10.nanoseconds, Nanoseconds(10).toDuration())
		assertEquals(Hours(1), Nanoseconds(3_600_000_000_000).toHours())
		assertEquals(10, Nanoseconds(10).toInt())
		assertEquals(10L, Nanoseconds(10).toLong())
		assertEquals(Microseconds(1), Nanoseconds(1_000).toMicroseconds())
		assertEquals(Milliseconds(1), Nanoseconds(1_000_000).toMilliseconds())
		assertEquals(Minutes(1), Nanoseconds(60_000_000_000).toMinutes())
		assertEquals(PreciseDuration.of(nanoseconds = 10), Nanoseconds(10).toPreciseDuration())
		assertEquals(Seconds(1), Nanoseconds(1_000_000_000).toSeconds())
		assertEquals("10", Nanoseconds(10).toString())

		assertEquals(Nanoseconds(10), 10.nanoseconds.toNanoseconds())
	}


	@Test
	fun testMap() {
		assertEquals(Nanoseconds(20), Nanoseconds(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Nanoseconds(-1).isNegative)
		assertFalse(Nanoseconds(0).isNegative)
		assertFalse(Nanoseconds(1).isNegative)

		assertFalse(Nanoseconds(-1).isPositive)
		assertFalse(Nanoseconds(0).isPositive)
		assertTrue(Nanoseconds(1).isPositive)

		assertFalse(Nanoseconds(-1).isZero)
		assertTrue(Nanoseconds(0).isZero)
		assertFalse(Nanoseconds(1).isZero)
	}
}
