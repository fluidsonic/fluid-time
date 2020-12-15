package io.fluidsonic.time

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Timestamp::class)
public object TimestampSerializer : KSerializer<Timestamp> {

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("kotlinx.datetime.Instant", PrimitiveKind.STRING)


	override fun serialize(encoder: Encoder, value: Timestamp) {
		encoder.encodeString(value.toString())
	}


	override fun deserialize(decoder: Decoder): Timestamp =
		Timestamp.parse(decoder.decodeString())
}
