package io.fluidsonic.time

import platform.darwin.*


internal actual inline fun NSInteger.toInt(): Int =
	toInt()


internal actual inline fun NSInteger.toLong(): Long =
	this
