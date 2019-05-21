package com.github.fluidsonic.fluid.time


class TimeZone private constructor(internal val platform: Platform_TimeZone) {

	val id = platform.id


	fun daylightSavingTimeOffset(timestamp: Timestamp) =
		platform.daylightSavingTimeOffset(timestamp)


	override fun equals(other: Any?) =
		this === other || (other is TimeZone && id == other.id)


	override fun hashCode() =
		id.hashCode()


	fun isInDaylightSavingTime(timestamp: Timestamp) =
		platform.isDaylightSavingTime(timestamp)


	fun nextDaylightSavingTimeTransition(after: Timestamp) =
		platform.nextDaylightSavingTimeTransition(after)


	override fun toString() =
		id


	companion object {

		val knownIds get() = platform_knownTimeZoneIds
		val system get() = withId(platform_systemTimeZoneId)!!
		val utc = TimeZone(platform_timeZoneWithId("UTC")!!)


		// TODO add some caching
		fun withId(id: String) = when (id) {
			"UTC" -> utc
			else -> platform_timeZoneWithId(id)?.let(::TimeZone)
		}
	}
}


expect class Platform_TimeZone

internal expect fun Platform_TimeZone.daylightSavingTimeOffset(timestamp: Timestamp): Seconds
internal expect val Platform_TimeZone.id: String
internal expect fun Platform_TimeZone.isDaylightSavingTime(timestamp: Timestamp): Boolean
internal expect fun Platform_TimeZone.nextDaylightSavingTimeTransition(after: Timestamp): Timestamp?

internal expect val platform_knownTimeZoneIds: Set<String>
internal expect val platform_systemTimeZoneId: String
internal expect fun platform_timeZoneWithId(id: String): Platform_TimeZone?

internal fun Platform_TimeZone.toCommon() =
	TimeZone.withId(id)
