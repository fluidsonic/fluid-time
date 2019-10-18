package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*
import kotlin.time.*


object MonthsTest {

	@Test
	fun testArithmethic() {
		assertEquals(Months(1), Months(-1).absolute)
		assertEquals(Months(0), Months(0).absolute)
		assertEquals(Months(1), Months(1).absolute)

		assertEquals(Months(5), Months(10) / 2)
		assertEquals(Months(5), Months(10L) / 2)
		assertEquals(5, Months(10) / Months(2))

		assertEquals(Months(2), Months(10) - Months(8))

		assertEquals(Months(18), Months(10) + Months(8))

		assertEquals(Months(1), Months(10) % 3)
		assertEquals(Months(1), Months(10) % 3L)
		assertEquals(Months(1), Months(10) % Months(3))

		assertEquals(Months(20), Months(10) * 2)
		assertEquals(Months(20), Months(10) * 2L)

		assertEquals(Months(-10), -Months(10))

		assertEquals(Months(20), 2 * Months(10))
		assertEquals(Months(20), 2L * Months(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Months(1).compareTo(Months(2)) < 0)
		assertTrue(Months(1).compareTo(Months(1)) == 0)
		assertTrue(Months(2).compareTo(Months(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Months(Long.MAX_VALUE), Months.max)
		assertEquals(Months(Long.MIN_VALUE), Months.min)
		assertEquals(Months(0), Months.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Months(123).toInt())
		assertEquals(123L, Months(123L).toLong())
	}


	@Test
	@UseExperimental(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(10, Months(10).toInt())
		assertEquals(10L, Months(10).toLong())
		assertEquals("10", Months(10).toString())
	}


	@Test
	fun testMap() {
		assertEquals(Months(20), Months(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Months(-1).isNegative)
		assertFalse(Months(0).isNegative)
		assertFalse(Months(1).isNegative)

		assertFalse(Months(-1).isPositive)
		assertFalse(Months(0).isPositive)
		assertTrue(Months(1).isPositive)

		assertFalse(Months(-1).isZero)
		assertTrue(Months(0).isZero)
		assertFalse(Months(1).isZero)
	}
}
