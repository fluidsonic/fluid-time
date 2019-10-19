package io.fluidsonic.time


@Deprecated(
	message = "renamed to WallClock due to conflict with kotlin.time",
	level = DeprecationLevel.WARNING,
	replaceWith = ReplaceWith("WallClock", "io.fluidsonic.time.WallClock")
)
typealias Clock = WallClock
