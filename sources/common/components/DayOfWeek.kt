package com.github.fluidsonic.fluid.time

import kotlinx.serialization.*
import kotlinx.serialization.internal.*


@Serializable(with = DayOfWeekSerializer::class)
enum class DayOfWeek : DateTimeComponent<DayOfWeek, Days> {

	monday,
	tuesday,
	wednesday,
	thursday,
	friday,
	saturday,
	sunday;


	@Suppress("OVERRIDE_BY_INLINE")
	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override fun minus(other: Int) =
		minus(other.toLong())


	override fun minus(other: Long) =
		minus(Days(other))


	override fun minus(other: DayOfWeek) =
		Days(value - other.value) // FIXME


	override fun minus(other: Days) =
		plus(-(other % 7))


	override fun plus(other: Int) =
		plus(other.toLong())


	override fun plus(other: Long) =
		plus(Days(other))


	override fun plus(other: Days) =
		values()[((ordinal + (other.value % 7) + 7) % 7).toInt()]


	override fun toInt() =
		value.toInt()


	override fun toLong() =
		value


	override fun toString() =
		name


	val value: Long
		get() = ordinal + 1L


	companion object {

		val max = sunday
		val min = monday


		fun of(value: Long, firstDayOfWeek: DayOfWeek = monday): DayOfWeek { // FIXME
			check(value, inRange = min.value .. max.value, name = "day [of week]")

			return unchecked(value)
		}


		fun serializer(): KSerializer<DayOfWeek> =
			DayOfWeekSerializer


		internal fun unchecked(value: Long) =
			values()[(value - 1).toInt()]
	}
}


@Serializer(forClass = DayOfWeek::class)
internal object DayOfWeekSerializer : KSerializer<DayOfWeek> {

	override val descriptor = StringDescriptor.withName("com.github.fluidsonic.fluid.time.DayOfWeek")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			DayOfWeek.values().firstOrNull { it.name == string }
				?: throw SerializationException("Invalid day of week: $string - valid values: ${DayOfWeek.values().joinToString { it.name }}")
		}


	override fun serialize(encoder: Encoder, obj: DayOfWeek) {
		encoder.encodeString(obj.name)
	}
}
