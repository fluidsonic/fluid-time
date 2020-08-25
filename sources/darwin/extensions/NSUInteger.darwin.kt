package io.fluidsonic.time

import platform.darwin.*


internal expect inline fun NSUInteger.toUInt(): UInt
internal expect inline fun NSUInteger.toULong(): ULong
internal expect inline infix fun NSUInteger.or(other: NSUInteger): NSUInteger
