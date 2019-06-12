package com.github.fluidsonic.fluid.time

import com.github.fluidsonic.fluid.time.Platform_TimeZone_Static.knownTimeZoneIds
import com.github.fluidsonic.fluid.time.Platform_TimeZone_Static.systemTimeZoneId
import com.github.fluidsonic.fluid.time.Platform_TimeZone_Static.timeZoneWithId
import kotlinx.serialization.*
import kotlinx.serialization.internal.*


@Serializable(with = TimeZoneSerializer::class)
class TimeZone private constructor(internal val platform: Platform_TimeZone) {

	val id = platform.id


	init {
		freeze()
	}


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

		val knownIds get() = knownTimeZoneIds
		val system get() = withId(systemTimeZoneId)!!
		val utc = TimeZone(timeZoneWithId("UTC")!!)


		// TODO add some caching
		fun withId(id: String) = when (id) {
			"UTC" -> utc
			else -> timeZoneWithId(id)?.let(::TimeZone)
		}
	}
}


expect class Platform_TimeZone

internal expect fun Platform_TimeZone.daylightSavingTimeOffset(timestamp: Timestamp): Seconds
internal expect val Platform_TimeZone.id: String
internal expect fun Platform_TimeZone.isDaylightSavingTime(timestamp: Timestamp): Boolean
internal expect fun Platform_TimeZone.nextDaylightSavingTimeTransition(after: Timestamp): Timestamp?

internal expect object Platform_TimeZone_Static {

	val knownTimeZoneIds: Set<String>
	val systemTimeZoneId: String

	fun timeZoneWithId(id: String): Platform_TimeZone?
}

internal fun Platform_TimeZone.toCommon() =
	TimeZone.withId(id)


@Serializer(forClass = TimeZone::class)
internal object TimeZoneSerializer : KSerializer<TimeZone> {

	override val descriptor = StringDescriptor.withName("com.github.fluidsonic.fluid.time.TimeZone")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			TimeZone.withId(code) ?: throw SerializationException("Unknown TimeZone id: $code")
		}


	override fun serialize(encoder: Encoder, obj: TimeZone) {
		encoder.encodeString(obj.id)
	}
}
