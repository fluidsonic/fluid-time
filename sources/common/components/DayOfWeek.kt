@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

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


	override inline fun map(transform: (Long) -> Long) =
		of(transform(toLong()))


	override operator fun minus(other: DayOfWeek) =
		Days(toLong() - other.toLong()) // FIXME check


	override operator fun minus(other: Days) =
		plus(-(other % 7))


	override operator fun plus(other: Days) =
		values()[((ordinal + (other.toLong() % 7) + 7) % 7).toInt()]


	override fun toInt() =
		ordinal + 1


	override fun toLong() =
		ordinal + 1L


	override fun toString() =
		name


	companion object : DateTimeComponent.CompanionInterface<DayOfWeek> {

		/* override */ val max = sunday
		/* override */ val min = monday


		override fun of(value: Long) =
			of(value, firstDayOfWeek = monday)


		fun of(value: Long, firstDayOfWeek: DayOfWeek): DayOfWeek {
			check(value, inRange = min.toLong() .. max.toLong(), name = "day [of week]")

			return unchecked((value + firstDayOfWeek.ordinal) % 7)
		}


		fun serializer(): KSerializer<DayOfWeek> =
			DayOfWeekSerializer


		internal inline fun unchecked(value: Long) =
			values()[(value - 1).toInt()]
	}
}


@Serializer(forClass = DayOfWeek::class)
internal object DayOfWeekSerializer : KSerializer<DayOfWeek> {

	override val descriptor = StringDescriptor.withName("io.fluidsonic.time.DayOfWeek")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			DayOfWeek.values().firstOrNull { it.name == string }
				?: throw SerializationException("Invalid day of week: $string - valid values: ${DayOfWeek.values().joinToString { it.name }}")
		}


	override fun serialize(encoder: Encoder, obj: DayOfWeek) {
		encoder.encodeString(obj.name)
	}
}
