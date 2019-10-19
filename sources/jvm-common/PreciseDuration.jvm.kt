package io.fluidsonic.time


fun PreciseDuration.toPlatform(): PlatformPreciseDuration =
	PlatformPreciseDuration.ofSeconds(seconds.toLong(), partialNanoseconds.toLong())


fun PlatformPreciseDuration.toCommon() =
	PreciseDuration.of(seconds = seconds, nanoseconds = nano.toLong())
