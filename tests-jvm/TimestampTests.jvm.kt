import io.fluidsonic.time.*
import java.util.*
import kotlin.test.*
import kotlin.time.toJavaInstant
import kotlinx.datetime.*


class TimestampTestsJvm {

	@Test
	fun testToJavaDate() {
		val timestamp = Timestamp.fromEpochSeconds(0)

		assertEquals(
			expected = Date.from(timestamp.toJavaInstant()),
			actual = timestamp.toJavaDate()
		)
	}
}
