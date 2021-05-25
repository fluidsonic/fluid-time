import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*
import kotlinx.serialization.json.*


@ExperimentalTime
class SerializationTests {

	@Test
	fun testTimestamp() {
		listOf(
			Duration(days = -365, hours = -23, minutes = -59, seconds = -59, nanoseconds = -999_999_999),
			Duration.ZERO,
			Duration(days = 1, hours = 2, minutes = 3, seconds = 4, nanoseconds = 5),
			Duration(days = 365, hours = 23, minutes = 59, seconds = 59, nanoseconds = 999_999_999),
		).forEach { duration ->
			assertEquals(expected = JsonPrimitive(duration.toString()), Json.encodeToJsonElement(DurationSerializer, duration))
		}
	}
}
