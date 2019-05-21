package com.github.fluidsonic.fluid.time

import platform.Foundation.*
import kotlin.system.*


internal val platform_gregorianCalendar = NSCalendar.calendarWithIdentifier(NSCalendarIdentifierGregorian)!!


internal actual fun platform_millisecondsSince1970() =
	Milliseconds(getTimeMillis())


internal actual fun platform_timestamp() =
	Timestamp.of(secondsSince1970 = Seconds.zero, nanoseconds = Nanoseconds(getTimeNanos()))
