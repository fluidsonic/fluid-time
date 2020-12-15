@file:Suppress("INVISIBLE_REFERENCE")

import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class LocalTimeTest {

	@Test
	fun testAtDate() {
		assertEquals(
			expected = LocalDateTime(2020, 12, 15, 16, 2, 57, 999),
			actual = LocalTime(16, 2, 57, 999).atDate(LocalDate(2020, 12, 15))
		)
	}


	@Test
	fun testConstant() {
		assertEquals(expected = LocalTime(23, 59, 59, 999_999_999), actual = LocalTime.MAX)
		assertEquals(expected = LocalTime(0, 0), actual = LocalTime.MIN)
	}


	@Test
	fun testInvalidValues() {
		assertFailsWith<IllegalArgumentException> { LocalTime(-1, 0) }
		assertFailsWith<IllegalArgumentException> { LocalTime(24, 0) }
		assertFailsWith<IllegalArgumentException> { LocalTime(0, -1) }
		assertFailsWith<IllegalArgumentException> { LocalTime(0, 60) }
		assertFailsWith<IllegalArgumentException> { LocalTime(0, 0, -1) }
		assertFailsWith<IllegalArgumentException> { LocalTime(0, 0, 60) }
		assertFailsWith<IllegalArgumentException> { LocalTime(0, 0, 0, -1) }
		assertFailsWith<IllegalArgumentException> { LocalTime(0, 0, 0, 1_000_000_000) }
	}


	@Test
	fun testParse() {
		fun checkParsedComponents(
			value: String,
			hour: Int,
			minute: Int,
			second: Int,
			nanosecond: Int,
		) {
			checkComponents(value.toLocalTime(), hour, minute, second, nanosecond)
		}

		checkParsedComponents("18:12", 18, 12, 0, 0)
		checkParsedComponents("18:43:15", 18, 43, 15, 0)
		checkParsedComponents("18:43:15.100500", 18, 43, 15, 100500000)
		checkParsedComponents("23:59:59.990", 23, 59, 59, 990000000)
		checkParsedComponents("23:59:59.999990", 23, 59, 59, 999990000)
		checkParsedComponents("23:59:59.999999990", 23, 59, 59, 999999990)

		assertFailsWith<DateTimeFormatException> { LocalTime.parse("x") }
		assertFailsWith<DateTimeFormatException> { "24:00:00".toLocalTime() }
	}


	@Test
	fun testParseOrNull() {
		assertEquals(expected = LocalTime.parse("18:43:15"), actual = LocalTime.parseOrNull("18:43:15"))
		assertNull(Timestamp.parseOrNull("x"))
	}


	@Test
	fun testToString() {
		assertEquals(expected = "02:01", actual = LocalTime(2, 1, 0, 0).toString())
		assertEquals(expected = "23:59:01", actual = LocalTime(23, 59, 1, 0).toString())
		assertEquals(expected = "23:59:59.990", actual = LocalTime(23, 59, 59, 990000000).toString())
		assertEquals(expected = "23:59:59.999990", actual = LocalTime(23, 59, 59, 999990000).toString())
		assertEquals(expected = "23:59:59.999999990", actual = LocalTime(23, 59, 59, 999999990).toString())
	}
}


private fun checkComponents(
	value: LocalTime,
	hour: Int,
	minute: Int,
	second: Int = 0,
	nanosecond: Int = 0,
) {
	assertEquals(hour, value.hour, "hours")
	assertEquals(minute, value.minute, "minutes")
	assertEquals(second, value.second, "seconds")
	assertEquals(nanosecond, value.nanosecond, "nanoseconds")

	val fromComponents = LocalTime(hour, minute, second, nanosecond)
	checkEquals(fromComponents, value)
}


private fun checkEquals(expected: LocalTime, actual: LocalTime) {
	assertEquals(expected, actual)
	assertEquals(expected.hashCode(), actual.hashCode())
	assertEquals(expected.toString(), actual.toString())
}
