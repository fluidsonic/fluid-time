package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*
import kotlin.time.*


object MillisecondsTest {

	@Test
	fun testArithmethic() {
		assertEquals(Milliseconds(1), Milliseconds(-1).absolute)
		assertEquals(Milliseconds(0), Milliseconds(0).absolute)
		assertEquals(Milliseconds(1), Milliseconds(1).absolute)

		assertEquals(Milliseconds(5), Milliseconds(10) / 2)
		assertEquals(Milliseconds(5), Milliseconds(10L) / 2)
		assertEquals(5, Milliseconds(10) / Milliseconds(2))

		assertEquals(Milliseconds(2), Milliseconds(10) - Milliseconds(8))

		assertEquals(Milliseconds(18), Milliseconds(10) + Milliseconds(8))

		assertEquals(Milliseconds(1), Milliseconds(10) % 3)
		assertEquals(Milliseconds(1), Milliseconds(10) % 3L)
		assertEquals(Milliseconds(1), Milliseconds(10) % Milliseconds(3))

		assertEquals(Milliseconds(20), Milliseconds(10) * 2)
		assertEquals(Milliseconds(20), Milliseconds(10) * 2L)

		assertEquals(Milliseconds(-10), -Milliseconds(10))

		assertEquals(Milliseconds(20), 2 * Milliseconds(10))
		assertEquals(Milliseconds(20), 2L * Milliseconds(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Milliseconds(1).compareTo(Milliseconds(2)) < 0)
		assertTrue(Milliseconds(1).compareTo(Milliseconds(1)) == 0)
		assertTrue(Milliseconds(2).compareTo(Milliseconds(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Milliseconds(Long.MAX_VALUE), Milliseconds.max)
		assertEquals(Milliseconds(Long.MIN_VALUE), Milliseconds.min)
		assertEquals(Milliseconds(86_400_000), Milliseconds.perDay)
		assertEquals(Milliseconds(3_600_000), Milliseconds.perHour)
		assertEquals(Milliseconds(60_000), Milliseconds.perMinute)
		assertEquals(Milliseconds(1_000), Milliseconds.perSecond)
		assertEquals(Milliseconds(0), Milliseconds.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Milliseconds(123).toInt())
		assertEquals(123L, Milliseconds(123L).toLong())
	}


	@Test
	@UseExperimental(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), Milliseconds(86_400_000).toDays())
		assertEquals(10.milliseconds, Milliseconds(10).toDuration())
		assertEquals(Hours(1), Milliseconds(3_600_000).toHours())
		assertEquals(10, Milliseconds(10).toInt())
		assertEquals(10L, Milliseconds(10).toLong())
		assertEquals(Microseconds(10_000L), Milliseconds(10).toMicroseconds())
		assertEquals(Minutes(1), Milliseconds(60_000).toMinutes())
		assertEquals(Nanoseconds(10_000_000), Milliseconds(10).toNanoseconds())
		assertEquals(PreciseDuration.of(milliseconds = 10), Milliseconds(10).toPreciseDuration())
		assertEquals(Seconds(1), Milliseconds(1_000).toSeconds())
		assertEquals("10", Milliseconds(10).toString())

		assertEquals(Milliseconds(10), 10.milliseconds.toMilliseconds())
	}


	@Test
	fun testMap() {
		assertEquals(Milliseconds(20), Milliseconds(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Milliseconds(-1).isNegative)
		assertFalse(Milliseconds(0).isNegative)
		assertFalse(Milliseconds(1).isNegative)

		assertFalse(Milliseconds(-1).isPositive)
		assertFalse(Milliseconds(0).isPositive)
		assertTrue(Milliseconds(1).isPositive)

		assertFalse(Milliseconds(-1).isZero)
		assertTrue(Milliseconds(0).isZero)
		assertFalse(Milliseconds(1).isZero)
	}
}
