import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*


class TimeZoneTests {

	@Test
	fun testOfOrNull() {
		assertEquals(expected = TimeZone.of("Europe/Berlin"), actual = TimeZone.ofOrNull("Europe/Berlin"))
		assertNull(TimeZone.ofOrNull("America/Moscow"))
	}
}
