package io.fluidsonic.time

import kotlinx.datetime.*


/** Returns the [TimeZone] for the given [id], or `null` if the ID is invalid. */
public fun TimeZone.Companion.ofOrNull(id: String): TimeZone? =
	runCatching { of(id) }.getOrNull()
