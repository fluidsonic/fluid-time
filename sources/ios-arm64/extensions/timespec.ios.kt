package io.fluidsonic.time

import platform.posix.*


internal actual inline fun timespec.toMilliseconds(): Milliseconds =
	Milliseconds((tv_sec * 1_000L) + (tv_nsec / 1_000_000L))
