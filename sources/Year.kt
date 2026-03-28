package io.fluidsonic.time


/** Utility for year-related calculations. */
public class Year private constructor() {

	public companion object {

		/** Returns `true` if the given [year] is a leap year. */
		public fun isLeap(year: Int): Boolean =
			year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
	}
}
