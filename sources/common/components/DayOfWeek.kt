@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@Serializable(with = DayOfWeekSerializer::class)
public enum class DayOfWeek : DateTimeComponent<DayOfWeek, Days> {

	monday,
	tuesday,
	wednesday,
	thursday,
	friday,
	saturday,
	sunday;


	override inline fun map(transform: (Long) -> Long): DayOfWeek =
		of(transform(toLong()))


	override operator fun minus(other: DayOfWeek): Days =
		Days(toLong() - other.toLong()) // FIXME check


	override operator fun minus(other: Days): DayOfWeek =
		plus(-(other % 7))


	override operator fun plus(other: Days): DayOfWeek =
		values()[((ordinal + (other.toLong() % 7) + 7) % 7).toInt()]


	override fun toInt(): Int =
		ordinal + 1


	override fun toLong(): Long =
		ordinal + 1L


	override fun toString(): String =
		name


	public companion object : DateTimeComponent.CompanionInterface<DayOfWeek> {

		/* override */public val max: DayOfWeek = sunday
		/* override */public val min: DayOfWeek = monday


		override fun of(value: Long): DayOfWeek =
			of(value, firstDayOfWeek = monday)


		public fun of(value: Long, firstDayOfWeek: DayOfWeek): DayOfWeek {
			check(value, inRange = min.toLong() .. max.toLong(), name = "day [of week]")

			return unchecked((value + firstDayOfWeek.ordinal) % 7)
		}


		public fun serializer(): KSerializer<DayOfWeek> =
			DayOfWeekSerializer


		internal inline fun unchecked(value: Long) =
			values()[(value - 1).toInt()]
	}
}


@Serializer(forClass = DayOfWeek::class)
internal object DayOfWeekSerializer : KSerializer<DayOfWeek> {

	override val descriptor = PrimitiveSerialDescriptor("io.fluidsonic.time.DayOfWeek", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { string ->
			DayOfWeek.values().firstOrNull { it.name == string }
				?: throw SerializationException("Invalid day of week: $string - valid values: ${DayOfWeek.values().joinToString { it.name }}")
		}


	override fun serialize(encoder: Encoder, value: DayOfWeek) {
		encoder.encodeString(value.name)
	}
}
