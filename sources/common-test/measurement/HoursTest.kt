package tests

import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


object HoursTest {

	@Test
	fun testArithmethic() {
		assertEquals(Hours(1), Hours(-1).absolute)
		assertEquals(Hours(0), Hours(0).absolute)
		assertEquals(Hours(1), Hours(1).absolute)

		assertEquals(Hours(5), Hours(10) / 2)
		assertEquals(Hours(5), Hours(10L) / 2)
		assertEquals(5, Hours(10) / Hours(2))

		assertEquals(Hours(2), Hours(10) - Hours(8))

		assertEquals(Hours(18), Hours(10) + Hours(8))

		assertEquals(Hours(1), Hours(10) % 3)
		assertEquals(Hours(1), Hours(10) % 3L)
		assertEquals(Hours(1), Hours(10) % Hours(3))

		assertEquals(Hours(20), Hours(10) * 2)
		assertEquals(Hours(20), Hours(10) * 2L)

		assertEquals(Hours(-10), -Hours(10))

		assertEquals(Hours(20), 2 * Hours(10))
		assertEquals(Hours(20), 2L * Hours(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Hours(1).compareTo(Hours(2)) < 0)
		assertTrue(Hours(1).compareTo(Hours(1)) == 0)
		assertTrue(Hours(2).compareTo(Hours(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Hours(Long.MAX_VALUE), Hours.max)
		assertEquals(Hours(Long.MIN_VALUE), Hours.min)
		assertEquals(Hours(24), Hours.perDay)
		assertEquals(Hours(0), Hours.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Hours(123).toInt())
		assertEquals(123L, Hours(123L).toLong())
	}


	@Test
	@UseExperimental(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(Days(1), Hours(24).toDays())
		assertEquals(10.hours, Hours(10).toDuration())
		assertEquals(10, Hours(10).toInt())
		assertEquals(10L, Hours(10).toLong())
		assertEquals(Microseconds(36_000_000_000), Hours(10).toMicroseconds())
		assertEquals(Milliseconds(36_000_000), Hours(10).toMilliseconds())
		assertEquals(Minutes(600L), Hours(10).toMinutes())
		assertEquals(Nanoseconds(36_000_000_000_000), Hours(10).toNanoseconds())
		assertEquals(PreciseDuration.of(hours = 10), Hours(10).toPreciseDuration())
		assertEquals(Seconds(36_000), Hours(10).toSeconds())
		assertEquals("10", Hours(10).toString())

		assertEquals(Hours(10), 10.hours.toHours())
	}


	@Test
	fun testMap() {
		assertEquals(Hours(20), Hours(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Hours(-1).isNegative)
		assertFalse(Hours(0).isNegative)
		assertFalse(Hours(1).isNegative)

		assertFalse(Hours(-1).isPositive)
		assertFalse(Hours(0).isPositive)
		assertTrue(Hours(1).isPositive)

		assertFalse(Hours(-1).isZero)
		assertTrue(Hours(0).isZero)
		assertFalse(Hours(1).isZero)
	}
}
