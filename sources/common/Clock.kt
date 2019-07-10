package com.github.fluidsonic.fluid.time


interface Clock {

	val timeZone: TimeZone

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String

	fun localDate() = timestamp().toLocalDate(timeZone)
	fun localDateTime() = timestamp().toLocalDateTime(timeZone)
	fun localTime() = timestamp().toLocalTime(timeZone)
	fun millisecondsSince1970(): Milliseconds
	fun secondsSince1970(): Seconds
	fun timestamp(): Timestamp
	fun withTimeZone(timeZone: TimeZone): Clock


	companion object {

		val systemUtc: Clock = System(TimeZone.utc)


		fun fixed(timestamp: Timestamp, timeZone: TimeZone): Clock =
			Fixed(timestamp = timestamp, timeZone = timeZone)


		fun offset(offset: Duration, source: Clock): Clock =
			if (offset.isZero) source
			else Offset(offset = offset, source = source)


		fun tick(interval: Duration, source: Clock): Clock {
			require(!interval.isNegative) { "interval cannot be negative" }

			val nanoseconds = interval.toNanoseconds()
			if (nanoseconds <= Nanoseconds(1)) return source

			when {
				(nanoseconds % Nanoseconds.perMillisecond).isZero -> Unit
				(Nanoseconds.perSecond % nanoseconds).isZero -> Unit
				else -> throw IllegalArgumentException("interval must either be integer milliseconds or one second must be divisable by interval without remainder")
			}

			return Tick(interval = nanoseconds, source = source)
		}


		fun tickMilliseconds(source: Clock): Clock =
			Tick(interval = Milliseconds(1).toNanoseconds(), source = source)


		fun tickMinutes(source: Clock): Clock =
			Tick(interval = Minutes(1).toNanoseconds(), source = source)


		fun tickSeconds(source: Clock): Clock =
			Tick(interval = Seconds(1).toNanoseconds(), source = source)


		fun system(timeZone: TimeZone): Clock =
			if (timeZone == TimeZone.utc) systemUtc
			else System(timeZone)
	}


	private class Fixed(
		val timestamp: Timestamp,
		override val timeZone: TimeZone
	) : Clock {

		init {
			freeze()
		}


		override fun equals(other: Any?) =
			this === other || (other is Fixed && timestamp == other.timestamp && timeZone == other.timeZone)


		override fun hashCode() =
			timestamp.hashCode() xor timeZone.hashCode()


		override fun millisecondsSince1970() =
			timestamp.millisecondsSince1970


		override fun secondsSince1970() =
			timestamp.secondsSince1970


		override fun timestamp() =
			timestamp


		override fun toString() =
			"Clock.fixed(timestamp = $timestamp, timeZone = $timeZone)"


		override fun withTimeZone(timeZone: TimeZone): Clock {
			if (timeZone == this.timeZone) return this

			return Fixed(timestamp = timestamp, timeZone = timeZone)
		}
	}


	private class Offset(
		val offset: Duration,
		val source: Clock
	) : Clock {

		init {
			freeze()
		}


		override fun equals(other: Any?) =
			this === other || (other is Offset && offset == other.offset && source == other.source)


		override fun hashCode() =
			offset.hashCode() xor source.hashCode()


		override fun millisecondsSince1970() =
			if ((offset.partialNanoseconds % Nanoseconds.perMillisecond).isZero)
				source.millisecondsSince1970() + offset.toMilliseconds()
			else
				timestamp().millisecondsSince1970


		override fun secondsSince1970() =
			if (offset.partialNanoseconds.isZero)
				source.secondsSince1970() + offset.toSeconds()
			else
				timestamp().secondsSince1970


		override fun timestamp() =
			source.timestamp() + offset


		override val timeZone
			get() = source.timeZone


		override fun toString() =
			"Clock.offset(offset = $offset, base = $source)"


		override fun withTimeZone(timeZone: TimeZone): Clock {
			val base = source.withTimeZone(timeZone)
			if (base === this.source) return this

			return Offset(offset = offset, source = base)
		}
	}


	private class System(
		override val timeZone: TimeZone
	) : Clock {

		init {
			freeze()
		}


		override fun equals(other: Any?) =
			this === other || (other is System && timeZone == other.timeZone)


		override fun hashCode() =
			timeZone.hashCode()


		override fun millisecondsSince1970() =
			platform_millisecondsSince1970()


		override fun secondsSince1970() =
			millisecondsSince1970().toSeconds()


		override fun timestamp() =
			platform_timestamp()


		override fun toString() =
			"Clock.system(timeZone = $timeZone)"


		override fun withTimeZone(timeZone: TimeZone): Clock {
			if (timeZone == this.timeZone) return this

			return system(timeZone = timeZone)
		}
	}


	private class Tick(
		val interval: Nanoseconds,
		val source: Clock
	) : Clock {

		init {
			freeze()
		}


		override fun equals(other: Any?) =
			this === other || (other is Tick && interval == other.interval && source == other.source)


		override fun hashCode() =
			interval.hashCode() xor source.hashCode()


		override fun millisecondsSince1970(): Milliseconds {
			val milliseconds = source.millisecondsSince1970()

			return if (interval >= Nanoseconds.perMillisecond)
				milliseconds - (milliseconds % interval.toMilliseconds())
			else
				milliseconds
		}


		override fun secondsSince1970(): Seconds {
			val seconds = source.secondsSince1970()

			return if (interval >= Nanoseconds.perSecond)
				seconds - (seconds % interval.toSeconds())
			else
				seconds
		}


		override fun timestamp() = when {
			(interval % Nanoseconds.perSecond).isZero -> Timestamp.of(secondsSince1970())
			(interval % Nanoseconds.perMillisecond).isZero -> Timestamp.of(millisecondsSince1970())
			else -> {
				val timestamp = source.timestamp()
				timestamp - (Nanoseconds(timestamp.partialNanosecond.toLong()) % interval) // FIXME check this
			}
		}


		override val timeZone
			get() = source.timeZone


		override fun toString() =
			"Clock.tick(interval = ${Duration.of(nanoseconds = interval)}, base = $source)"


		override fun withTimeZone(timeZone: TimeZone): Clock {
			val base = source.withTimeZone(timeZone)
			if (base === this.source) return this

			return Tick(interval = interval, source = base)
		}
	}
}


internal expect fun platform_millisecondsSince1970(): Milliseconds
internal expect fun platform_timestamp(): Timestamp
