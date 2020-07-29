package io.fluidsonic.time


public fun LocalTime.toPlatform(): PlatformLocalTime =
	PlatformLocalTime.of(hour.toInt(), minute.toInt(), second.toInt(), nanosecond.toInt())


public fun PlatformLocalTime.toCommon(): LocalTime =
	LocalTime.of(
		hour = hour.toLong(),
		minute = minute.toLong(),
		second = second.toLong(),
		nanosecond = nano.toLong()
	)
