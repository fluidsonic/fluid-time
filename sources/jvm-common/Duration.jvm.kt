package com.github.fluidsonic.fluid.time


fun Duration.toPlatform(): PlatformDuration =
	PlatformDuration.ofSeconds(seconds.toLong(), partialNanoseconds.toLong())


fun PlatformDuration.toCommon() =
	Duration.of(seconds = seconds, nanoseconds = nano.toLong())
