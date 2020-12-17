package io.fluidsonic.time

import java.time.LocalTime as jtLocalTime
import java.time.*
import java.time.format.*
import kotlinx.datetime.*


public actual class LocalTime internal constructor(internal val value: jtLocalTime) : Comparable<LocalTime> {

	public actual constructor(hour: Int, minute: Int, second: Int, nanosecond: Int) :
		this(try {
			jtLocalTime.of(hour, minute, second, nanosecond)
		} catch (e: DateTimeException) {
			throw IllegalArgumentException(e)
		})

	public actual val hour: Int get() = value.hour
	public actual val minute: Int get() = value.minute
	public actual val second: Int get() = value.second
	public actual val nanosecond: Int get() = value.nano

	override fun equals(other: Any?): Boolean =
		(this === other) || (other is LocalTime && this.value == other.value)

	override fun hashCode(): Int = value.hashCode()

	actual override fun toString(): String = value.toString()

	actual override fun compareTo(other: LocalTime): Int = this.value.compareTo(other.value)


	public actual companion object {

		public actual fun parse(isoString: String): LocalTime =
			try {
				jtLocalTime.parse(isoString).let(::LocalTime)
			} catch (e: DateTimeParseException) {
				@Suppress("INVISIBLE_MEMBER")
				throw DateTimeFormatException(e)
			}

		public actual val min: LocalTime = LocalTime(jtLocalTime.MIN)
		public actual val max: LocalTime = LocalTime(jtLocalTime.MAX)
	}
}
