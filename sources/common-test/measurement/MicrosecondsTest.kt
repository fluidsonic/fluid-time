package tests

import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


object MicrosecondsTest {

	@Test
	fun testArithmethic() {
		assertEquals(Microseconds(1), Microseconds(-1).absolute)
		assertEquals(Microseconds(0), Microseconds(0).absolute)
		assertEquals(Microseconds(1), Microseconds(1).absolute)

		assertEquals(Microseconds(5), Microseconds(10) / 2)
		assertEquals(Microseconds(5), Microseconds(10L) / 2)
		assertEquals(5, Microseconds(10) / Microseconds(2))

		assertEquals(Microseconds(2), Microseconds(10) - Microseconds(8))

		assertEquals(Microseconds(18), Microseconds(10) + Microseconds(8))

		assertEquals(Microseconds(1), Microseconds(10) % 3)
		assertEquals(Microseconds(1), Microseconds(10) % 3L)
		assertEquals(Microseconds(1), Microseconds(10) % Microseconds(3))

		assertEquals(Microseconds(20), Microseconds(10) * 2)
		assertEquals(Microseconds(20), Microseconds(10) * 2L)

		assertEquals(Microseconds(-10), -Microseconds(10))

		assertEquals(Microseconds(20), 2 * Microseconds(10))
		assertEquals(Microseconds(20), 2L * Microseconds(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Microseconds(1).compareTo(Microseconds(2)) < 0)
		assertTrue(Microseconds(1).compareTo(Microseconds(1)) == 0)
		assertTrue(Microseconds(2).compareTo(Microseconds(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Microseconds(Long.MAX_VALUE), Microseconds.max)
		assertEquals(Microseconds(Long.MIN_VALUE), Microseconds.min)
		assertEquals(Microseconds(86_400_000_000), Microseconds.perDay)
		assertEquals(Microseconds(3_600_000_000), Microseconds.perHour)
		assertEquals(Microseconds(1_000), Microseconds.perMillisecond)
		assertEquals(Microseconds(60_000_000), Microseconds.perMinute)
		assertEquals(Microseconds(1_000_000), Microseconds.perSecond)
		assertEquals(Microseconds(0), Microseconds.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Microseconds(123).toInt())
		assertEquals(123L, Microseconds(123L).toLong())
	}


	@Test
	@OptIn(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), Microseconds(86_400_000_000).toDays())
		assertEquals(10.microseconds, Microseconds(10).toDuration())
		assertEquals(Hours(1), Microseconds(3_600_000_000).toHours())
		assertEquals(10, Microseconds(10).toInt())
		assertEquals(10L, Microseconds(10).toLong())
		assertEquals(Milliseconds(1), Microseconds(1_000).toMilliseconds())
		assertEquals(Minutes(1), Microseconds(60_000_000).toMinutes())
		assertEquals(Nanoseconds(10_000), Microseconds(10).toNanoseconds())
		assertEquals(PreciseDuration.of(microseconds = 10), Microseconds(10).toPreciseDuration())
		assertEquals(Seconds(1), Microseconds(1_000_000).toSeconds())
		assertEquals("10", Microseconds(10).toString())

		assertEquals(Microseconds(10), 10.microseconds.toMicroseconds())
	}


	@Test
	fun testMap() {
		assertEquals(Microseconds(20), Microseconds(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Microseconds(-1).isNegative)
		assertFalse(Microseconds(0).isNegative)
		assertFalse(Microseconds(1).isNegative)

		assertFalse(Microseconds(-1).isPositive)
		assertFalse(Microseconds(0).isPositive)
		assertTrue(Microseconds(1).isPositive)

		assertFalse(Microseconds(-1).isZero)
		assertTrue(Microseconds(0).isZero)
		assertFalse(Microseconds(1).isZero)
	}
}
