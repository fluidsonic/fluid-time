package io.fluidsonic.time


@PublishedApi
@Suppress("NOTHING_TO_INLINE")
internal inline fun check(value: Long, inRange: LongRange, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }
