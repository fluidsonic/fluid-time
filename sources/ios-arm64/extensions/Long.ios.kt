package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun Long.toNSInteger(): NSInteger =
	this
