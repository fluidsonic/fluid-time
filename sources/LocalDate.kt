package io.fluidsonic.time

import kotlinx.datetime.*


public fun LocalDate.atTime(time: LocalTime): LocalDateTime =
	time.atDate(this)


public fun LocalDate.Companion.parseOrNull(isoString: String): LocalDate? =
	runCatching { parse(isoString) }.getOrNull()
