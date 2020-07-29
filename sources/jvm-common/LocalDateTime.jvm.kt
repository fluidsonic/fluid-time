package io.fluidsonic.time


public actual fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp =
	toPlatform().atZone(timeZone.toPlatform()).toInstant().toCommon()


public actual fun LocalDateTime.toDayOfWeek(): DayOfWeek =
	PlatformDayOfWeek.from(toPlatform()).toCommon()


public fun LocalDateTime.toPlatform(): PlatformLocalDateTime =
	PlatformLocalDateTime.of(
		date.year.toInt(),
		date.month.toInt(),
		date.day.toInt(),
		time.hour.toInt(),
		time.minute.toInt(),
		time.second.toInt(),
		time.nanosecond.toInt()
	)


public fun PlatformLocalDateTime.toCommon(): LocalDateTime =
	LocalDateTime.of(
		year = year.toLong(),
		month = monthValue.toLong(),
		day = dayOfMonth.toLong(),
		hour = hour.toLong(),
		minute = minute.toLong(),
		second = second.toLong(),
		nanosecond = nano.toLong()
	)
