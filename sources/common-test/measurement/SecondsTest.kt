package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*
import kotlin.time.*


object SecondsTest {

	@Test
	fun testArithmethic() {
		assertEquals(Seconds(1), Seconds(-1).absolute)
		assertEquals(Seconds(0), Seconds(0).absolute)
		assertEquals(Seconds(1), Seconds(1).absolute)

		assertEquals(Seconds(5), Seconds(10) / 2)
		assertEquals(Seconds(5), Seconds(10L) / 2)
		assertEquals(5, Seconds(10) / Seconds(2))

		assertEquals(Seconds(2), Seconds(10) - Seconds(8))

		assertEquals(Seconds(18), Seconds(10) + Seconds(8))

		assertEquals(Seconds(1), Seconds(10) % 3)
		assertEquals(Seconds(1), Seconds(10) % 3L)
		assertEquals(Seconds(1), Seconds(10) % Seconds(3))

		assertEquals(Seconds(20), Seconds(10) * 2)
		assertEquals(Seconds(20), Seconds(10) * 2L)

		assertEquals(Seconds(-10), -Seconds(10))

		assertEquals(Seconds(20), 2 * Seconds(10))
		assertEquals(Seconds(20), 2L * Seconds(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Seconds(1).compareTo(Seconds(2)) < 0)
		assertTrue(Seconds(1).compareTo(Seconds(1)) == 0)
		assertTrue(Seconds(2).compareTo(Seconds(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Seconds(Long.MAX_VALUE), Seconds.max)
		assertEquals(Seconds(Long.MIN_VALUE), Seconds.min)
		assertEquals(Seconds(86_400), Seconds.perDay)
		assertEquals(Seconds(3_600), Seconds.perHour)
		assertEquals(Seconds(60), Seconds.perMinute)
		assertEquals(Seconds(0), Seconds.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Seconds(123).toInt())
		assertEquals(123L, Seconds(123L).toLong())
	}


	@Test
	@UseExperimental(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), Seconds(86_400).toDays())
		assertEquals(10.seconds, Seconds(10).toDuration())
		assertEquals(Hours(1), Seconds(3_600).toHours())
		assertEquals(10, Seconds(10).toInt())
		assertEquals(10L, Seconds(10).toLong())
		assertEquals(Microseconds(10_000_000), Seconds(10).toMicroseconds())
		assertEquals(Milliseconds(10_000), Seconds(10).toMilliseconds())
		assertEquals(Minutes(1), Seconds(60).toMinutes())
		assertEquals(Nanoseconds(10_000_000_000), Seconds(10).toNanoseconds())
		assertEquals(PreciseDuration.of(seconds = 10), Seconds(10).toPreciseDuration())
		assertEquals("10", Seconds(10).toString())

		assertEquals(Seconds(10), 10.seconds.toSeconds())
	}


	@Test
	fun testMap() {
		assertEquals(Seconds(20), Seconds(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Seconds(-1).isNegative)
		assertFalse(Seconds(0).isNegative)
		assertFalse(Seconds(1).isNegative)

		assertFalse(Seconds(-1).isPositive)
		assertFalse(Seconds(0).isPositive)
		assertTrue(Seconds(1).isPositive)

		assertFalse(Seconds(-1).isZero)
		assertTrue(Seconds(0).isZero)
		assertFalse(Seconds(1).isZero)
	}
}
