package io.fluidsonic.time

import kotlinx.datetime.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDate::class)
public object LocalDateSerializer : KSerializer<LocalDate> {

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("kotlinx.datetime.LocalDate", PrimitiveKind.STRING)


	override fun serialize(encoder: Encoder, value: LocalDate) {
		encoder.encodeString(value.toString())
	}


	override fun deserialize(decoder: Decoder): LocalDate =
		LocalDate.parse(decoder.decodeString())
}
