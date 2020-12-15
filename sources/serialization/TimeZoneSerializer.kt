package io.fluidsonic.time

import kotlinx.datetime.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = TimeZone::class)
public object TimeZoneSerializer : KSerializer<TimeZone> {

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("kotlinx.datetime.TimeZone", PrimitiveKind.STRING)


	override fun serialize(encoder: Encoder, value: TimeZone) {
		encoder.encodeString(value.id)
	}


	override fun deserialize(decoder: Decoder): TimeZone =
		TimeZone.of(decoder.decodeString())
}
