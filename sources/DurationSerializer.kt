package io.fluidsonic.time

import kotlin.time.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


public object DurationSerializer : KSerializer<Duration> {

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("io.fluidsonic.time.Duration", PrimitiveKind.STRING)


	override fun serialize(encoder: Encoder, value: Duration) {
		encoder.encodeString(value.toString())
	}


	override fun deserialize(decoder: Decoder): Duration =
		Duration.parse(decoder.decodeString())
}
