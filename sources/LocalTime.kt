package io.fluidsonic.time

import kotlinx.datetime.*


private val _max = LocalTime(23, 59, 59, 999_999_999)
private val _min = LocalTime(0, 0)


public val LocalTime.Companion.max: LocalTime
	get() = _max


public val LocalTime.Companion.midnight: LocalTime
	get() = _min


public val LocalTime.Companion.min: LocalTime
	get() = _min


public fun LocalTime.Companion.parseOrNull(isoString: String): LocalTime? =
	runCatching { parse(isoString) }.getOrNull()
