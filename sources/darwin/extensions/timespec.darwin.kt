package io.fluidsonic.time

import platform.posix.*


internal expect inline fun timespec.toMilliseconds(): Milliseconds
