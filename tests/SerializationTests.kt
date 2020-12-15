import io.fluidsonic.time.*
import kotlin.test.*
import kotlinx.datetime.*
import kotlinx.serialization.json.*


class SerializationTests {

	@Test
	fun testLocalDate() {
		listOf(
			LocalDate(-999_999, 1, 1),
			LocalDate(2020, 12, 15),
			LocalDate(999_999, 12, 31),
		).forEach { date ->
			assertEquals(expected = JsonPrimitive(date.toString()), Json.encodeToJsonElement(LocalDateSerializer, date))
		}
	}


	@Test
	fun testLocalDateTime() {
		listOf(
			LocalDateTime(-999_999, 1, 1, 0, 0, 0, 0),
			LocalDateTime(2020, 12, 15, 16, 24, 6, 1234),
			LocalDateTime(999_999, 12, 31, 23, 59, 59, 999_999_999),
		).forEach { dateTime ->
			assertEquals(expected = JsonPrimitive(dateTime.toString()), Json.encodeToJsonElement(LocalDateTimeSerializer, dateTime))
		}
	}


	@Test
	fun testLocalTime() {
		listOf(
			LocalTime(0, 0, 0, 0),
			LocalTime(16, 24, 6, 1234),
			LocalTime(23, 59, 59, 999_999_999),
		).forEach { time ->
			assertEquals(expected = JsonPrimitive(time.toString()), Json.encodeToJsonElement(LocalTimeSerializer, time))
		}
	}


	@Test
	fun testTimestamp() {
		listOf(
			LocalDateTime(-999_999, 1, 1, 0, 0, 0, 0),
			LocalDateTime(2020, 12, 15, 16, 24, 6, 1234),
			LocalDateTime(999_999, 12, 31, 23, 59, 59, 999_999_999),
		).forEach { dateTime ->
			val timestamp = dateTime.toTimestamp(TimeZone.UTC)
			assertEquals(expected = JsonPrimitive(timestamp.toString()), Json.encodeToJsonElement(TimestampSerializer, timestamp))
		}
	}


	@Test
	fun testTimeZone() {
		listOf(
			TimeZone.UTC,
			TimeZone.currentSystemDefault(),
			TimeZone.of("Europe/Berlin"),
		).forEach { timeZone ->
			assertEquals(expected = JsonPrimitive(timeZone.id), Json.encodeToJsonElement(TimeZoneSerializer, timeZone))
		}
	}
}
