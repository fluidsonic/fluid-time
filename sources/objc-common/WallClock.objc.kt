package io.fluidsonic.time

import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.*
import kotlin.native.concurrent.*


@ThreadLocal
internal val platform_gregorianCalendar = NSCalendar.calendarWithIdentifier(NSCalendarIdentifierGregorian)!!


internal actual fun platform_millisecondsSince1970() =
	memScoped {
		val time = alloc<timespec>()
		val result = clock_gettime(CLOCK_REALTIME, time.ptr)
		check(result == 0)

		Milliseconds((time.tv_sec * 1_000) + (time.tv_nsec / 1_000_000))
	}


internal actual fun platform_timestamp() =
	memScoped {
		val time = alloc<timespec>()
		val result = clock_gettime(CLOCK_REALTIME, time.ptr)
		check(result == 0)

		Timestamp.of(secondsSince1970 = Seconds(time.tv_sec), nanoseconds = Nanoseconds(time.tv_nsec))
	}
