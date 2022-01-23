import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*


@OptIn(ExperimentalTime::class)
class DurationTests {

	@Test
	fun testFactory() {
		Duration(days = 1, hours = 2, minutes = 3, seconds = 4, nanoseconds = 5).toComponents { days, hours, minutes, seconds, nanoseconds ->
			assertEquals(expected = 1, actual = days)
			assertEquals(expected = 2, actual = hours)
			assertEquals(expected = 3, actual = minutes)
			assertEquals(expected = 4, actual = seconds)
			assertEquals(expected = 5, actual = nanoseconds)
		}
	}
}
