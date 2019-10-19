package io.fluidsonic.time

import io.fluidsonic.time.PlatformTimeZoneStatic.knownTimeZoneIds
import io.fluidsonic.time.PlatformTimeZoneStatic.systemTimeZoneId
import io.fluidsonic.time.PlatformTimeZoneStatic.timeZoneWithId
import kotlinx.serialization.*
import kotlinx.serialization.internal.*


@Serializable(with = TimeZoneSerializer::class)
class TimeZone private constructor(internal val platform: PlatformTimeZone) {

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


expect class PlatformTimeZone

internal expect fun PlatformTimeZone.daylightSavingTimeOffset(timestamp: Timestamp): Seconds
internal expect val PlatformTimeZone.id: String
internal expect fun PlatformTimeZone.isDaylightSavingTime(timestamp: Timestamp): Boolean
internal expect fun PlatformTimeZone.nextDaylightSavingTimeTransition(after: Timestamp): Timestamp?

internal expect object PlatformTimeZoneStatic {

	val knownTimeZoneIds: Set<String>
	val systemTimeZoneId: String

	fun timeZoneWithId(id: String): PlatformTimeZone?
}

internal fun PlatformTimeZone.toCommon() =
	TimeZone.withId(id)


@Serializer(forClass = TimeZone::class)
internal object TimeZoneSerializer : KSerializer<TimeZone> {

	override val descriptor = StringDescriptor.withName("io.fluidsonic.time.TimeZone")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			TimeZone.withId(code) ?: throw SerializationException("Unknown TimeZone id: $code")
		}


	override fun serialize(encoder: Encoder, obj: TimeZone) {
		encoder.encodeString(obj.id)
	}
}
