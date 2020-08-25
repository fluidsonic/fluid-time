package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun NSUInteger.toUInt(): UInt =
	this


internal actual inline fun NSUInteger.toULong(): ULong =
	toULong()


internal actual inline infix fun NSUInteger.or(other: NSUInteger): NSUInteger =
	this or other
