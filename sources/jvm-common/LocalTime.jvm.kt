package io.fluidsonic.time


fun LocalTime.toPlatform(): PlatformLocalTime =
	PlatformLocalTime.of(hour.toInt(), minute.toInt(), second.toInt(), nanosecond.toInt())


fun PlatformLocalTime.toCommon() =
	LocalTime.of(
		hour = hour.toLong(),
		minute = minute.toLong(),
		second = second.toLong(),
		nanosecond = nano.toLong()
	)
