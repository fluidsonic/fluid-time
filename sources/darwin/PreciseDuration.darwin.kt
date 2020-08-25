package io.fluidsonic.time

import platform.Foundation.*


public fun PreciseDuration.Companion.of(platform: NSTimeInterval): PreciseDuration =
	of(seconds = platform.toLong(), nanoseconds = ((platform % 1) * Nanoseconds.perSecond.toLong()).toLong())


public fun PreciseDuration.toPlatform(): NSTimeInterval =
	seconds.toLong().toDouble() + (partialNanoseconds.toLong().toDouble() / Nanoseconds.perSecond.toLong())
