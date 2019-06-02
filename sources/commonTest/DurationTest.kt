package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*


object DurationTest {

	@Test
	fun testJson() = assertJsonSerialization(
		value = Duration.of(hours = 48, minutes = 12, seconds = 34, milliseconds = 567),
		json = """ "PT48H12M34.567S" """,
		serializer = Duration.serializer()
	)
}
