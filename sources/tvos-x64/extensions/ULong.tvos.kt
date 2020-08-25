package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun ULong.toNSUInteger(): NSUInteger =
	this
