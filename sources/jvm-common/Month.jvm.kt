package com.github.fluidsonic.fluid.time


fun MonthOfYear.toPlatform() = when (this) {
	MonthOfYear.january -> PlatformMonth.JANUARY
	MonthOfYear.february -> PlatformMonth.FEBRUARY
	MonthOfYear.march -> PlatformMonth.MARCH
	MonthOfYear.april -> PlatformMonth.APRIL
	MonthOfYear.may -> PlatformMonth.MAY
	MonthOfYear.june -> PlatformMonth.JUNE
	MonthOfYear.july -> PlatformMonth.JULY
	MonthOfYear.august -> PlatformMonth.AUGUST
	MonthOfYear.september -> PlatformMonth.SEPTEMBER
	MonthOfYear.october -> PlatformMonth.OCTOBER
	MonthOfYear.november -> PlatformMonth.NOVEMBER
	MonthOfYear.december -> PlatformMonth.DECEMBER
}


fun PlatformMonth.toCommon() = when (this) {
	PlatformMonth.JANUARY -> MonthOfYear.january
	PlatformMonth.FEBRUARY -> MonthOfYear.february
	PlatformMonth.MARCH -> MonthOfYear.march
	PlatformMonth.APRIL -> MonthOfYear.april
	PlatformMonth.MAY -> MonthOfYear.may
	PlatformMonth.JUNE -> MonthOfYear.june
	PlatformMonth.JULY -> MonthOfYear.july
	PlatformMonth.AUGUST -> MonthOfYear.august
	PlatformMonth.SEPTEMBER -> MonthOfYear.september
	PlatformMonth.OCTOBER -> MonthOfYear.october
	PlatformMonth.NOVEMBER -> MonthOfYear.november
	PlatformMonth.DECEMBER -> MonthOfYear.december
}
