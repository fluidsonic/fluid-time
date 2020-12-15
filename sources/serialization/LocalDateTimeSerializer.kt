package io.fluidsonic.time

import kotlinx.datetime.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDateTime::class)
public object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("kotlinx.datetime.LocalDateTime", PrimitiveKind.STRING)


	override fun serialize(encoder: Encoder, value: LocalDateTime) {
		encoder.encodeString(value.toString())
	}


	override fun deserialize(decoder: Decoder): LocalDateTime =
		LocalDateTime.parse(decoder.decodeString())
}
