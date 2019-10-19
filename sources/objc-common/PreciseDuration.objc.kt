package io.fluidsonic.time

import platform.Foundation.*


fun PreciseDuration.Companion.of(platform: NSTimeInterval) =
	of(seconds = platform.toLong(), nanoseconds = ((platform % 1) * Nanoseconds.perSecond.toLong()).toLong())


fun PreciseDuration.toPlatform(): NSTimeInterval =
	seconds.toLong().toDouble() + (partialNanoseconds.toLong().toDouble() / Nanoseconds.perSecond.toLong())
