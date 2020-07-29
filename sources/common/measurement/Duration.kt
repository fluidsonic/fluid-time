package io.fluidsonic.time


@Deprecated(
	message = "renamed to PreciseDuration due to conflict with kotlin.time",
	level = DeprecationLevel.WARNING,
	replaceWith = ReplaceWith("PreciseDuration", "io.fluidsonic.time.PreciseDuration")
)
public typealias Duration = PreciseDuration
