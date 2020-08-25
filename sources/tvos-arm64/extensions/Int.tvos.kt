package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun Int.toNSInteger(): NSInteger =
	toLong()
