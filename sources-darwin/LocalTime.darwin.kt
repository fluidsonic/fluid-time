@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package io.fluidsonic.time

import kotlinx.datetime.LocalTime as KLocalTime


public actual class LocalTime private constructor(private val delegate: KLocalTime) : Comparable<LocalTime> {

	public actual val hour: Int get() = delegate.hour
	public actual val minute: Int get() = delegate.minute
	public actual val second: Int get() = delegate.second
	public actual val nanosecond: Int get() = delegate.nanosecond


	public actual constructor(hour: Int, minute: Int, second: Int, nanosecond: Int) :
		this(KLocalTime.of(hour, minute, second, nanosecond))


	public actual override operator fun compareTo(other: LocalTime): Int =
		delegate.compareTo(other.delegate)


	override fun equals(other: Any?): Boolean =
		this === other || (other is LocalTime && delegate.equals(other.delegate))


	override fun hashCode(): Int =
		delegate.hashCode()


	public actual override fun toString(): String =
		delegate.toString()


	public actual companion object {

		public actual val MIN: LocalTime = LocalTime(KLocalTime.MIN)
		public actual val MAX: LocalTime = LocalTime(KLocalTime.MAX)


		public actual fun parse(isoString: String): LocalTime =
			LocalTime(KLocalTime.parse(isoString))
	}
}
