package com.github.fluidsonic.fluid.time

import platform.Foundation.*


fun Duration.Companion.of(platform: NSTimeInterval) =
	of(seconds = platform.toLong(), nanoseconds = ((platform % 1) * Nanoseconds.perSecond.toLong()).toLong())


fun Duration.toPlatform(): NSTimeInterval =
	seconds.value.toDouble() + (partialNanoseconds.value.toDouble() / Nanoseconds.perSecond.value)
