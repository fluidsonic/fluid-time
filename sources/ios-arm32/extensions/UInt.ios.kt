package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun UInt.toNSUInteger(): NSUInteger =
	this
