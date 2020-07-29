package io.fluidsonic.time

import java.util.*


public actual fun Timestamp.toDayOfWeek(timeZone: TimeZone): DayOfWeek =
	toPlatform().atZone(timeZone.toPlatform()).dayOfWeek.toCommon()


public actual fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate =
	toPlatform().atZone(timeZone.toPlatform()).toLocalDate().toCommon()


public actual fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime =
	toPlatform().atZone(timeZone.toPlatform()).toLocalDateTime().toCommon()


public actual fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime =
	toPlatform().atZone(timeZone.toPlatform()).toLocalTime().toCommon()


public fun Timestamp.toPlatform(): PlatformTimestamp =
	PlatformTimestamp.ofEpochSecond(secondsSince1970.toLong(), partialNanosecond.toLong())


public fun Timestamp.toPlatformDate(): Date =
	Date(millisecondsSince1970.value)


public fun PlatformTimestamp.toCommon(): Timestamp =
	Timestamp.of(secondsSince1970 = Seconds(epochSecond), nanoseconds = Nanoseconds(nano))
