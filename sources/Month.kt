package io.fluidsonic.time

import kotlinx.datetime.*


public fun Month.daysIn(year: Int): Int =
	when (this) {
		Month.JANUARY -> 31
		Month.FEBRUARY -> if (Year.isLeap(year)) 29 else 28
		Month.MARCH -> 31
		Month.APRIL -> 30
		Month.MAY -> 31
		Month.JUNE -> 30
		Month.JULY -> 31
		Month.AUGUST -> 31
		Month.SEPTEMBER -> 30
		Month.OCTOBER -> 31
		Month.NOVEMBER -> 30
		Month.DECEMBER -> 31
	}
