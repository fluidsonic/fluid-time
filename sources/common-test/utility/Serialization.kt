package tests

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlin.test.*


private val jsonSerializer = Json(JsonConfiguration.Stable)


internal inline fun <reified Value : Any> assertJsonSerialization(
	value: Value,
	json: String,
	serializer: KSerializer<Value>,
	noinline equals: ((a: Value, b: Value) -> Boolean)? = null
) {
	val actualJson = jsonSerializer.stringify(serializer, value)
	val actualStructure = try {
		jsonSerializer.parse(JsonElement.serializer(), actualJson)
	}
	catch (e: Exception) {
		throw Exception("Cannot parse actual JSON:\n$actualJson", e)
	}

	val expectedStructure = try {
		jsonSerializer.parse(JsonElement.serializer(), json)
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
