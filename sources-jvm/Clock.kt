package io.fluidsonic.time

import java.time.Clock as JavaClock
import java.time.*
import java.time.Instant
import kotlinx.datetime.*
import kotlinx.datetime.Clock


public fun Clock.toJavaClock(timeZone: TimeZone = TimeZone.UTC): JavaClock =
	KotlinClock(this, timeZone.toJavaZoneId())


private class KotlinClock(
	private val clock: Clock,
	private val timeZone: ZoneId,
) : JavaClock() {

	override fun getZone(): ZoneId =
		timeZone


	override fun instant(): Instant =
		clock.now().toJavaInstant()


	override fun millis(): Long =
		clock.now().toEpochMilliseconds()


	override fun toString() =
		"KotlinClock(clock=$clock, timeZone=$timeZone)"


	override fun withZone(zone: ZoneId) =
		when (zone) {
			timeZone -> this
			else -> KotlinClock(clock, zone)
		}
}
