package io.fluidsonic.time


@Deprecated(
	message = "renamed to PrecideDuration due to conflict with kotlin.time",
	level = DeprecationLevel.WARNING,
	replaceWith = ReplaceWith("PrediceDuration", "io.fluidsonic.time.PreciseDuration")
)
typealias Duration = PreciseDuration
