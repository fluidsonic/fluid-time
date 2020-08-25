package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun NSUInteger.toUInt(): UInt =
	toUInt()


internal actual inline fun NSUInteger.toULong(): ULong =
	this


internal actual inline infix fun NSUInteger.or(other: NSUInteger): NSUInteger =
	this or other
