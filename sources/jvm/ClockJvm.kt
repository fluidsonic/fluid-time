package com.github.fluidsonic.fluid.time

import org.threeten.bp.Clock as PlatformClock
import org.threeten.bp.DayOfWeek as PlatformDayOfWeek
import org.threeten.bp.LocalDate as PlatformLocalDate
import org.threeten.bp.LocalDateTime as PlatformLocalDateTime
import org.threeten.bp.LocalTime as PlatformLocalTime


internal actual fun platform_millisecondsSince1970() =
	Milliseconds(System.currentTimeMillis())


internal actual fun platform_timestamp() =
	Timestamp.of(platform_millisecondsSince1970()) // TODO think about precision & performance
