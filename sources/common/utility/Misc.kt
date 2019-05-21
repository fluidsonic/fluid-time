package com.github.fluidsonic.fluid.time


@Suppress("NOTHING_TO_INLINE")
inline fun check(value: Int, inRange: IntRange, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
inline fun check(value: Int, inRange: LongRange, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
inline fun check(value: Long, inRange: IntRange, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
inline fun check(value: Long, inRange: LongRange, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
inline fun <Value : Comparable<Value>> check(value: Value, inRange: ClosedRange<Value>, name: String) =
	check(value in inRange) { "$name must be in range $inRange: $value" }
