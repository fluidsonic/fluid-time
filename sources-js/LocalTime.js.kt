package io.fluidsonic.time

import kotlinx.datetime.internal.JSJoda.LocalTime as jtLocalTime
import kotlinx.datetime.*


public actual class LocalTime internal constructor(internal val value: jtLocalTime) : Comparable<LocalTime> {

	public actual constructor(hour: Int, minute: Int, second: Int, nanosecond: Int) :
		this(try {
			jtLocalTime.of(hour, minute, second, nanosecond)
		} catch (e: Throwable) {
			@Suppress("INVISIBLE_MEMBER")
			if (e.isJodaDateTimeException()) throw IllegalArgumentException(e)
			throw e
		})

	public actual val hour: Int get() = value.hour().toInt()
	public actual val minute: Int get() = value.minute().toInt()
	public actual val second: Int get() = value.second().toInt()
	public actual val nanosecond: Int get() = value.nano().toInt()

	override fun equals(other: Any?): Boolean =
		(this === other) || (other is LocalTime && this.value == other.value)

	override fun hashCode(): Int = value.hashCode().toInt()

	actual override fun toString(): String = value.toString()

	actual override fun compareTo(other: LocalTime): Int = this.value.compareTo(other.value).toInt()


	public actual companion object {

		public actual fun parse(isoString: String): LocalTime =
			try {
				jtLocalTime.parse(isoString).let(::LocalTime)
			} catch (e: Throwable) {
				@Suppress("INVISIBLE_MEMBER")
				if (e.isJodaDateTimeParseException()) throw DateTimeFormatException(e)
				throw e
			}

		public actual val min: LocalTime = LocalTime(jtLocalTime.MIN)
		public actual val max: LocalTime = LocalTime(jtLocalTime.MAX)
	}
}
