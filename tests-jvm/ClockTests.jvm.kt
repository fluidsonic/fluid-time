import io.fluidsonic.time.*
import java.time.ZoneId
import kotlin.test.*
import kotlin.time.toJavaInstant
import kotlinx.datetime.*


class ClockTestsJvm {

	@Test
	fun testToJavaClock() {
		val clock = ManualClock()
		clock.set(Timestamp.fromEpochSeconds(1_000_000))

		val javaClock = clock.toJavaClock()

		assertEquals(
			expected = clock.now().toEpochMilliseconds(),
			actual = javaClock.millis(),
		)
	}


	@Test
	fun testToJavaClockDefaultTimeZone() {
		val clock = ManualClock()
		clock.set(Timestamp.fromEpochSeconds(0))

		val javaClock = clock.toJavaClock()

		assertEquals(
			expected = ZoneId.of("UTC"),
			actual = javaClock.zone,
		)
	}


	@Test
	fun testToJavaClockWithTimeZone() {
		val clock = ManualClock()
		clock.set(Timestamp.fromEpochSeconds(0))

		val timeZone = TimeZone.of("Europe/Berlin")
		val javaClock = clock.toJavaClock(timeZone)

		assertEquals(
			expected = ZoneId.of("Europe/Berlin"),
			actual = javaClock.zone,
		)
	}


	@Test
	fun testToJavaClockWithZone() {
		val clock = ManualClock()
		clock.set(Timestamp.fromEpochSeconds(0))

		val javaClock = clock.toJavaClock()
		val newZone = ZoneId.of("America/New_York")
		val rezoned = javaClock.withZone(newZone)

		assertEquals(expected = newZone, actual = rezoned.zone)
		assertEquals(expected = javaClock.millis(), actual = rezoned.millis())
	}


	@Test
	fun testToJavaClockWithZoneSameZoneReturnsSame() {
		val clock = ManualClock()
		clock.set(Timestamp.fromEpochSeconds(0))

		val javaClock = clock.toJavaClock()
		val same = javaClock.withZone(javaClock.zone)

		assertSame(expected = javaClock, actual = same)
	}


	@Test
	fun testToJavaClockInstant() {
		val clock = ManualClock()
		clock.set(Timestamp.fromEpochSeconds(1_700_000_000))

		val javaClock = clock.toJavaClock()

		assertEquals(
			expected = clock.now().toJavaInstant(),
			actual = javaClock.instant(),
		)
	}
}
