package io.fluidsonic.time

import io.fluidsonic.time.PlatformTimeZoneStatic.knownTimeZoneIds
import io.fluidsonic.time.PlatformTimeZoneStatic.systemTimeZoneId
import io.fluidsonic.time.PlatformTimeZoneStatic.timeZoneWithId
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@Serializable(with = TimeZoneSerializer::class)
public class TimeZone private constructor(internal val platform: PlatformTimeZone) {

	public val id: String = platform.id


	init {
		freeze()
	}


	public fun daylightSavingTimeOffset(timestamp: Timestamp): Seconds =
		platform.daylightSavingTimeOffset(timestamp)


	override fun equals(other: Any?): Boolean =
		this === other || (other is TimeZone && id == other.id)


	override fun hashCode(): Int =
		id.hashCode()


	public fun isInDaylightSavingTime(timestamp: Timestamp): Boolean =
		platform.isDaylightSavingTime(timestamp)


	public fun nextDaylightSavingTimeTransition(after: Timestamp): Timestamp? =
		platform.nextDaylightSavingTimeTransition(after)


	override fun toString(): String =
		id


	public companion object {

		public val knownIds: Set<String> get() = knownTimeZoneIds
		public val system: TimeZone get() = withId(systemTimeZoneId)!!
		public val utc: TimeZone = TimeZone(timeZoneWithId("UTC")!!)


		// TODO add some caching
		public fun withId(id: String): TimeZone? = when (id) {
			"UTC" -> utc
			else -> timeZoneWithId(id)?.let(::TimeZone)
		}
	}
}


public expect class PlatformTimeZone

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

	override val descriptor = PrimitiveSerialDescriptor("io.fluidsonic.time.TimeZone", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			TimeZone.withId(code) ?: throw SerializationException("Unknown TimeZone id: $code")
		}


	override fun serialize(encoder: Encoder, value: TimeZone) {
		encoder.encodeString(value.id)
	}
}
