package tests

import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


class MinutesTest {

	@Test
	fun testArithmethic() {
		assertEquals(Minutes(1), Minutes(-1).absolute)
		assertEquals(Minutes(0), Minutes(0).absolute)
		assertEquals(Minutes(1), Minutes(1).absolute)

		assertEquals(Minutes(5), Minutes(10) / 2)
		assertEquals(Minutes(5), Minutes(10L) / 2)
		assertEquals(5, Minutes(10) / Minutes(2))

		assertEquals(Minutes(2), Minutes(10) - Minutes(8))

		assertEquals(Minutes(18), Minutes(10) + Minutes(8))

		assertEquals(Minutes(1), Minutes(10) % 3)
		assertEquals(Minutes(1), Minutes(10) % 3L)
		assertEquals(Minutes(1), Minutes(10) % Minutes(3))

		assertEquals(Minutes(20), Minutes(10) * 2)
		assertEquals(Minutes(20), Minutes(10) * 2L)

		assertEquals(Minutes(-10), -Minutes(10))

		assertEquals(Minutes(20), 2 * Minutes(10))
		assertEquals(Minutes(20), 2L * Minutes(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Minutes(1).compareTo(Minutes(2)) < 0)
		assertTrue(Minutes(1).compareTo(Minutes(1)) == 0)
		assertTrue(Minutes(2).compareTo(Minutes(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Minutes(Long.MAX_VALUE), Minutes.max)
		assertEquals(Minutes(Long.MIN_VALUE), Minutes.min)
		assertEquals(Minutes(1_440), Minutes.perDay)
		assertEquals(Minutes(60), Minutes.perHour)
		assertEquals(Minutes(0), Minutes.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Minutes(123).toInt())
		assertEquals(123L, Minutes(123L).toLong())
	}


	@Test
	@OptIn(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), Minutes(1_440).toDays())
		assertEquals(10.minutes, Minutes(10).toDuration())
		assertEquals(Hours(1), Minutes(60).toHours())
		assertEquals(10, Minutes(10).toInt())
		assertEquals(10L, Minutes(10).toLong())
		assertEquals(Microseconds(600_000_000), Minutes(10).toMicroseconds())
		assertEquals(Milliseconds(600_000), Minutes(10).toMilliseconds())
		assertEquals(Nanoseconds(600_000_000_000), Minutes(10).toNanoseconds())
		assertEquals(PreciseDuration.of(minutes = 10), Minutes(10).toPreciseDuration())
		assertEquals(Seconds(600L), Minutes(10).toSeconds())
		assertEquals("10", Minutes(10).toString())

		assertEquals(Minutes(10), 10.minutes.toMinutes())
	}


	@Test
	fun testMap() {
		assertEquals(Minutes(20), Minutes(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Minutes(-1).isNegative)
		assertFalse(Minutes(0).isNegative)
		assertFalse(Minutes(1).isNegative)

		assertFalse(Minutes(-1).isPositive)
		assertFalse(Minutes(0).isPositive)
		assertTrue(Minutes(1).isPositive)

		assertFalse(Minutes(-1).isZero)
		assertTrue(Minutes(0).isZero)
		assertFalse(Minutes(1).isZero)
	}
}
