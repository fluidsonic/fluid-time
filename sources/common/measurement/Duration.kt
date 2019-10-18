package com.github.fluidsonic.fluid.time


@Deprecated(
	message = "renamed to PrecideDuration due to conflict with kotlin.time",
	level = DeprecationLevel.WARNING,
	replaceWith = ReplaceWith("PrediceDuration", "com.github.fluidsonic.fluid.time.PreciseDuration")
)
typealias Duration = PreciseDuration
