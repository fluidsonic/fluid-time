import io.fluidsonic.time.*
import kotlin.test.*
import kotlin.time.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.datetime.*


@OptIn(ExperimentalTime::class)
class ManualClockTests {

	@Test
	fun testAdvance() {
		val clock = ManualClock()
		val epoch = Timestamp.fromEpochSeconds(0)
		val aLittleAfterEpoch = epoch.plus(DateTimePeriod(hours = 1, minutes = 2, seconds = 3, nanoseconds = 4), timeZone = TimeZone.UTC)
		val aLotAfterEpoch =
			epoch.plus(DateTimePeriod(years = 1, months = 2, days = 3, hours = 4, minutes = 5, seconds = 6, nanoseconds = 7), timeZone = TimeZone.UTC)

		clock.set(epoch)

		clock.advance() // odd, should we prevent this?
		clock.advance(seconds(0))
		clock.advance(hours = 0)
		clock.advance(period = DatePeriod(), timeZone = TimeZone.UTC)
		clock.advance(period = DateTimePeriod(), timeZone = TimeZone.UTC)
		assertEquals(expected = epoch, actual = clock.now())

		clock.advance(duration = hours(1) + minutes(2) + seconds(3) + nanoseconds(4))
		assertEquals(expected = aLittleAfterEpoch, actual = clock.now())

		clock.set(epoch)
		clock.advance(hours = 1, minutes = 2, seconds = 3, nanoseconds = 4)
		assertEquals(expected = aLittleAfterEpoch, actual = clock.now())

		clock.set(epoch)
		clock.advance(period = DateTimePeriod(1, 2, 3, 4, 5, 6, 7), timeZone = TimeZone.UTC)
		assertEquals(expected = aLotAfterEpoch, actual = clock.now())

		clock.set(epoch)
		clock.advance(years = 1, months = 2, days = 3, hours = 4, minutes = 5, seconds = 6, nanoseconds = 7, timeZone = TimeZone.UTC)
		assertEquals(expected = aLotAfterEpoch, actual = clock.now())
	}


	@Test
	fun testNow() {
		val clock = ManualClock()
		assertNull(clock.nowOrNull())
		assertFailsWith<IllegalStateException> { clock.now() }

		val timestamp = Timestamp.fromEpochSeconds(0)
		clock.set(timestamp)

		assertEquals(expected = timestamp, actual = clock.now())
		assertEquals(expected = timestamp, actual = clock.nowOrNull())

		clock.set(null)
		assertNull(clock.nowOrNull())
		assertFailsWith<IllegalStateException> { clock.now() }
	}


	@Test
	fun testSet() {
		val clock = ManualClock()
		val epoch = Timestamp.fromEpochSeconds(0)
		val aLittleAfterEpoch = epoch.plus(DateTimePeriod(hours = 1, minutes = 2, seconds = 3, nanoseconds = 4), timeZone = TimeZone.UTC)

		clock.set(date = LocalDate(1970, 1, 1), timeZone = TimeZone.UTC)
		assertEquals(expected = epoch, actual = clock.now())

		clock.set(null)
		assertNull(clock.nowOrNull())

		clock.set(dateTime = LocalDateTime(1970, 1, 1, 1, 2, 3, 4), timeZone = TimeZone.UTC)
		assertEquals(expected = aLittleAfterEpoch, actual = clock.now())

		clock.set(null)
		assertNull(clock.nowOrNull())

		clock.set(year = 1970, month = 1, day = 1, hour = 1, minute = 2, second = 3, nanosecond = 4, timeZone = TimeZone.UTC)
		assertEquals(expected = aLittleAfterEpoch, actual = clock.now())
	}
}
