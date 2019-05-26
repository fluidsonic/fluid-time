package tests

import com.github.fluidsonic.fluid.time.*
import kotlin.test.*


object TimestampTest {

	@Test
	fun testJson() = assertJsonSerialization(
		value = Timestamp.firstIn1970,
		json = """ "1970-01-01T00:00Z" """,
		serializer = Timestamp.serializer()
	)
}
