package com.github.fluidsonic.fluid.time


@Deprecated(
	message = "renamed to WallClock due to conflict with kotlin.time",
	level = DeprecationLevel.WARNING,
	replaceWith = ReplaceWith("WallClock", "com.github.fluidsonic.fluid.time.WallClock")
)
typealias Clock = WallClock
