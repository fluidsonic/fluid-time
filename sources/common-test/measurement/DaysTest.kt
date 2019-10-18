package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*
import kotlin.time.*


object DaysTest {

	@Test
	fun testArithmethic() {
		assertEquals(Days(1), Days(-1).absolute)
		assertEquals(Days(0), Days(0).absolute)
		assertEquals(Days(1), Days(1).absolute)

		assertEquals(Days(5), Days(10) / 2)
		assertEquals(Days(5), Days(10L) / 2)
		assertEquals(5, Days(10) / Days(2))

		assertEquals(Days(2), Days(10) - Days(8))

		assertEquals(Days(18), Days(10) + Days(8))

		assertEquals(Days(1), Days(10) % 3)
		assertEquals(Days(1), Days(10) % 3L)
		assertEquals(Days(1), Days(10) % Days(3))

		assertEquals(Days(20), Days(10) * 2)
		assertEquals(Days(20), Days(10) * 2L)

		assertEquals(Days(-10), -Days(10))

		assertEquals(Days(20), 2 * Days(10))
		assertEquals(Days(20), 2L * Days(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Days(1).compareTo(Days(2)) < 0)
		assertTrue(Days(1).compareTo(Days(1)) == 0)
		assertTrue(Days(2).compareTo(Days(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Days(Long.MAX_VALUE), Days.max)
		assertEquals(Days(Long.MIN_VALUE), Days.min)
		assertEquals(Days(0), Days.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Days(123).toInt())
		assertEquals(123L, Days(123L).toLong())
	}


	@Test
	@UseExperimental(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(10.days, Days(10).toDuration())
		assertEquals(Hours(240), Days(10).toHours())
		assertEquals(10, Days(10).toInt())
		assertEquals(10L, Days(10).toLong())
		assertEquals(Microseconds(864_000_000_000L), Days(10).toMicroseconds())
		assertEquals(Milliseconds(864_000_000L), Days(10).toMilliseconds())
		assertEquals(Minutes(14_400L), Days(10).toMinutes())
		assertEquals(Nanoseconds(864_000_000_000_000L), Days(10).toNanoseconds())
		assertEquals(PreciseDuration.of(days = 10), Days(10).toPreciseDuration())
		assertEquals(Seconds(864_000L), Days(10).toSeconds())
		assertEquals("10", Days(10).toString())

		assertEquals(Days(10), 10.days.toDays())
	}


	@Test
	fun testIsNegative() {
		assertTrue(Days(-1).isNegative)
		assertFalse(Days(0).isNegative)
		assertFalse(Days(1).isNegative)
	}


	@Test
	fun testIsZero() {
		assertFalse(Days(-1).isZero)
		assertTrue(Days(0).isZero)
		assertFalse(Days(1).isZero)
	}


	@Test
	fun testMap() {
		assertEquals(Days(20), Days(10).map { it * 2 })
	}
}
