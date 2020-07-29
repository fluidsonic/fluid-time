package tests

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlin.test.*


internal inline fun <reified Value : Any> assertJsonSerialization(
	value: Value,
	json: String,
	serializer: KSerializer<Value>,
	noinline equals: ((a: Value, b: Value) -> Boolean)? = null
) {
	val actualJson = Json.encodeToString(serializer, value)
	val actualStructure = try {
		Json.decodeFromString(JsonElement.serializer(), actualJson)
	}
	catch (e: Exception) {
		throw Exception("Cannot parse actual JSON:\n$actualJson", e)
	}

	val expectedStructure = try {
		Json.decodeFromString(JsonElement.serializer(), json)
	}
	catch (e: Exception) {
		throw Exception("Cannot parse expected JSON:\n$json", e)
	}

	assertEquals(expectedStructure, actualStructure, "serialized value")

	val actualValue = Json.decodeFromString(serializer, json)

	if (equals != null)
		assertTrue(equals(value, actualValue), "parsed value ==> expected: $value but was: $actualValue")
	else
		assertEquals(value, actualValue, "parsed value")
}
