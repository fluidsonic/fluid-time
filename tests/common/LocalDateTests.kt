package tests

import io.fluidsonic.time.*
import kotlin.test.*


class LocalDateTests {

	@Test
	fun testDaysSince() {
		assertEquals(
			expected = Days(-366),
			actual = LocalDate.of(2020, 1, 1)
				.daysSince(LocalDate.of(2021, 1, 1))
		)

		assertEquals(
			expected = Days(-31),
			actual = LocalDate.of(2020, 1, 1)
				.daysSince(LocalDate.of(2020, 2, 1))
		)

		assertEquals(
			expected = Days(-1),
			actual = LocalDate.of(2020, 1, 1)
				.daysSince(LocalDate.of(2020, 1, 2))
		)

		assertEquals(
			expected = Days(-365),
			actual = LocalDate.of(2020, 1, 1)
				.daysSince(LocalDate.of(2020, 12, 31))
		)
	}


	@Test
	fun testDaysUntil() {
		assertEquals(
			expected = Days(366),
			actual = LocalDate.of(2020, 1, 1)
				.daysUntil(LocalDate.of(2021, 1, 1))
		)

		assertEquals(
			expected = Days(31),
			actual = LocalDate.of(2020, 1, 1)
				.daysUntil(LocalDate.of(2020, 2, 1))
		)

		assertEquals(
			expected = Days(1),
			actual = LocalDate.of(2020, 1, 1)
				.daysUntil(LocalDate.of(2020, 1, 2))
		)

		assertEquals(
			expected = Days(365),
			actual = LocalDate.of(2020, 1, 1)
				.daysUntil(LocalDate.of(2020, 12, 31))
		)
	}
}
