package io.fluidsonic.time


public fun PreciseDuration.toPlatform(): PlatformPreciseDuration =
	PlatformPreciseDuration.ofSeconds(seconds.toLong(), partialNanoseconds.toLong())


public fun PlatformPreciseDuration.toCommon(): PreciseDuration =
	PreciseDuration.of(seconds = seconds, nanoseconds = nano.toLong())
