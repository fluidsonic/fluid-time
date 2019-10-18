package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*


object PreciseDurationTest {

	@Test
	fun testJson() = assertJsonSerialization(
		value = PreciseDuration.of(hours = 48, minutes = 12, seconds = 34, milliseconds = 567),
		json = """ "PT48H12M34.567S" """,
		serializer = PreciseDuration.serializer()
	)


	@Test
	fun testZero() {
		assertEquals(expected = Seconds.zero, actual = PreciseDuration.zero.seconds)
		assertEquals(expected = Nanoseconds.zero, actual = PreciseDuration.zero.partialNanoseconds)
	}
}
