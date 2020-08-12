@file:Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")

package io.fluidsonic.time

import io.fluidsonic.locale.*
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


	public fun displayName(locale: Locale, format: Format = Format.full): String =
		DayOfWeek_displayName(this, locale = locale, format = format)


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


		override fun isValid(value: Long): Boolean =
			value in min.toLong() .. max.toLong()


		override fun of(value: Long): DayOfWeek {
			require(isValid(value)) { "Day of week must be in range ${min.toLong()} .. ${max.toLong()}: $value" }

			return unchecked(value)
		}


		public fun of(value: Long, firstDayOfWeek: DayOfWeek): DayOfWeek {
			require(isValid(value)) { "Day of week must be in range ${min.toLong()} .. ${max.toLong()}: $value" }

			return unchecked((value + firstDayOfWeek.ordinal) % 7)
		}


		override fun ofOrNull(value: Long): DayOfWeek? {
			if (!isValid(value))
				return null

			return unchecked(value)
		}


		public fun ofOrNull(value: Long, firstDayOfWeek: DayOfWeek): DayOfWeek? {
			if (!isValid(value))
				return null

			return unchecked((value + firstDayOfWeek.ordinal) % 7)
		}


		public fun serializer(): KSerializer<DayOfWeek> =
			DayOfWeekSerializer


		internal inline fun unchecked(value: Long) =
			values()[(value - 1).toInt()]
	}


	public enum class Format {

		character,            // M T W T F S S
		characterStandalone,  // M T W T F S S
		short,                // Mo Tu We Th Fr Sa Su
		shortStandalone,      // Mo Tu We Th Fr Sa Su
		medium,               // Mon Tue Wed Thu Fri Sat Sun
		mediumStandalone,     // Mon Tue Wed Thu Fri Sat Sun
		full,                 // Monday Tuesday Wednesday Thursday Friday Saturday Sunday
		fullStandalone        // Monday Tuesday Wednesday Thursday Friday Saturday Sunday
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


internal expect fun DayOfWeek_displayName(dayOfWeek: DayOfWeek, locale: Locale, format: DayOfWeek.Format): String
