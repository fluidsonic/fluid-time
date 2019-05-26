package tests

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlin.test.*


private val jsonSerializer = Json(JsonConfiguration.Stable)


@UseExperimental(ImplicitReflectionSerializer::class)
internal inline fun <reified Value : Any> assertJsonSerialization(
	value: Value,
	json: String,
	serializer: KSerializer<Value> = Value::class.serializer(),
	noinline equals: ((a: Value, b: Value) -> Boolean)? = null
) {
	val actualJson = jsonSerializer.stringify(serializer, value)
	val actualStructure = try {
		Json.parse<JsonElement>(actualJson)
	}
	catch (e: Exception) {
		throw Exception("Cannot parse actual JSON:\n$actualJson", e)
	}

	val expectedStructure = try {
		Json.parse<JsonElement>(json)
	}
	catch (e: Exception) {
		throw Exception("Cannot parse expected JSON:\n$json", e)
	}

	assertEquals(expectedStructure, actualStructure, "serialized value")

	val actualValue = jsonSerializer.parse(serializer, json)

	if (equals != null)
		assertTrue(equals(value, actualValue), "parsed value ==> expected: $value but was: $actualValue")
	else
		assertEquals(value, actualValue, "parsed value")
}
