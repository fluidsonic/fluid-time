package tests

import io.fluidsonic.time.*
import kotlin.test.*


class PeriodTests {

	@Test
	fun testBetween() {
		assertEquals(
			expected = Period(1),
			actual = Period.between(
				startInclusive = LocalDate.of(2020, 1, 1),
				endExclusive = LocalDate.of(2021, 1, 1)
			)
		)

		assertEquals(
			expected = Period(0, 1),
			actual = Period.between(
				startInclusive = LocalDate.of(2020, 1, 1),
				endExclusive = LocalDate.of(2020, 2, 1)
			)
		)

		assertEquals(
			expected = Period(0, 0, 1),
			actual = Period.between(
				startInclusive = LocalDate.of(2020, 1, 1),
				endExclusive = LocalDate.of(2020, 1, 2)
			)
		)

		assertEquals(
			expected = Period(0, 11, 30),
			actual = Period.between(
				startInclusive = LocalDate.of(2020, 1, 1),
				endExclusive = LocalDate.of(2020, 12, 31)
			)
		)
	}
}
