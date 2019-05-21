package com.github.fluidsonic.fluid.time

import org.threeten.bp.Duration as PlatformDuration


fun Duration.toPlatform(): PlatformDuration =
	PlatformDuration.ofSeconds(seconds.toLong(), partialNanoseconds.toLong())


fun PlatformDuration.toCommon() =
	Duration.of(seconds = seconds, nanoseconds = nano.toLong())
