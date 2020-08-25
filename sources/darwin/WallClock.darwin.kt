package io.fluidsonic.time

import kotlin.native.concurrent.*
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.*


@ThreadLocal
internal val platform_gregorianCalendar = NSCalendar.calendarWithIdentifier(NSCalendarIdentifierGregorian)!!


internal actual fun platform_millisecondsSince1970(): Milliseconds =
	memScoped {
		val time = alloc<timespec>()
		val result = clock_gettime(CLOCK_REALTIME, time.ptr)
		check(result == 0)

		time.toMilliseconds()
	}


internal actual fun platform_timestamp() =
	Timestamp.of(millisecondsSince1970 = platform_millisecondsSince1970())
