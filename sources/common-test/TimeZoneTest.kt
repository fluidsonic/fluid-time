package tests

import io.fluidsonic.time.*
import kotlin.test.*


object TimeZoneTest {

	@Test
	fun testJsonUTC() = assertJsonSerialization(
		value = TimeZone.utc,
		json = """ "UTC" """,
		serializer = TimeZone.serializer()
	)


	@Test
	fun testUTC() {
		assertEquals("UTC", TimeZone.utc.id)
	}
}
