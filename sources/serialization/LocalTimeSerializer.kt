package io.fluidsonic.time

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalTime::class)
public object LocalTimeSerializer : KSerializer<LocalTime> {

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("kotlinx.datetime.LocalTime", PrimitiveKind.STRING)


	override fun serialize(encoder: Encoder, value: LocalTime) {
		encoder.encodeString(value.toString())
	}


	override fun deserialize(decoder: Decoder): LocalTime =
		LocalTime.parse(decoder.decodeString())
}
