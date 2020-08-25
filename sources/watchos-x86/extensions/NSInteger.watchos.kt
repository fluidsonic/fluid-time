package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun NSInteger.toInt(): Int =
	this


internal actual inline fun NSInteger.toLong(): Long =
	toLong()
