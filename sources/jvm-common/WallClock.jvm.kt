package io.fluidsonic.time


internal actual fun platform_millisecondsSince1970() =
	Milliseconds(System.currentTimeMillis())


internal actual fun platform_timestamp() =
	Timestamp.of(platform_millisecondsSince1970()) // TODO think about precision & performance
