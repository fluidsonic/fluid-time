package tests

import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


class YearsTest {

	@Test
	fun testArithmethic() {
		assertEquals(Years(1), Years(-1).absolute)
		assertEquals(Years(0), Years(0).absolute)
		assertEquals(Years(1), Years(1).absolute)

		assertEquals(Years(5), Years(10) / 2)
		assertEquals(Years(5), Years(10L) / 2)
		assertEquals(5, Years(10) / Years(2))

		assertEquals(Years(2), Years(10) - Years(8))

		assertEquals(Years(18), Years(10) + Years(8))

		assertEquals(Years(1), Years(10) % 3)
		assertEquals(Years(1), Years(10) % 3L)
		assertEquals(Years(1), Years(10) % Years(3))

		assertEquals(Years(20), Years(10) * 2)
		assertEquals(Years(20), Years(10) * 2L)

		assertEquals(Years(-10), -Years(10))

		assertEquals(Years(20), 2 * Years(10))
		assertEquals(Years(20), 2L * Years(10))
	}


	@Suppress("ReplaceCallWithBinaryOperator")
	@Test
	fun testComparison() {
		assertTrue(Years(1).compareTo(Years(2)) < 0)
		assertTrue(Years(1).compareTo(Years(1)) == 0)
		assertTrue(Years(2).compareTo(Years(1)) > 0)
	}


	@Test
	fun testConstants() {
		assertEquals(Years(Long.MAX_VALUE), Years.max)
		assertEquals(Years(Long.MIN_VALUE), Years.min)
		assertEquals(Years(0), Years.zero)
	}


	@Test
	fun testConstruction() {
		assertEquals(123, Years(123).toInt())
		assertEquals(123L, Years(123L).toLong())
	}


	@Test
	@OptIn(ExperimentalTime::class)
	fun testConversion() {
		assertEquals(10, Years(10).toInt())
		assertEquals(10L, Years(10).toLong())
		assertEquals("10", Years(10).toString())
	}


	@Test
	fun testMap() {
		assertEquals(Years(20), Years(10).map { it * 2 })
	}


	@Test
	fun testTests() {
		assertTrue(Years(-1).isNegative)
		assertFalse(Years(0).isNegative)
		assertFalse(Years(1).isNegative)

		assertFalse(Years(-1).isPositive)
		assertFalse(Years(0).isPositive)
		assertTrue(Years(1).isPositive)

		assertFalse(Years(-1).isZero)
		assertTrue(Years(0).isZero)
		assertFalse(Years(1).isZero)
	}
}
