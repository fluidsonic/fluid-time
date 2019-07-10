package com.github.fluidsonic.fluid.time


@Suppress("NOTHING_TO_INLINE")
inline fun check(value: Long, inRange: LongRange, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }
