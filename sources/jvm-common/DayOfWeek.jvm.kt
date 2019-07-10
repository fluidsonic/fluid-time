package com.github.fluidsonic.fluid.time


fun DayOfWeek.toPlatform() = when (this) {
	DayOfWeek.monday -> PlatformDayOfWeek.MONDAY
	DayOfWeek.tuesday -> PlatformDayOfWeek.TUESDAY
	DayOfWeek.wednesday -> PlatformDayOfWeek.WEDNESDAY
	DayOfWeek.thursday -> PlatformDayOfWeek.THURSDAY
	DayOfWeek.friday -> PlatformDayOfWeek.FRIDAY
	DayOfWeek.saturday -> PlatformDayOfWeek.SATURDAY
	DayOfWeek.sunday -> PlatformDayOfWeek.SUNDAY
}


fun PlatformDayOfWeek.toCommon() = when (this) {
	PlatformDayOfWeek.MONDAY -> DayOfWeek.monday
	PlatformDayOfWeek.TUESDAY -> DayOfWeek.tuesday
	PlatformDayOfWeek.WEDNESDAY -> DayOfWeek.wednesday
	PlatformDayOfWeek.THURSDAY -> DayOfWeek.thursday
	PlatformDayOfWeek.FRIDAY -> DayOfWeek.friday
	PlatformDayOfWeek.SATURDAY -> DayOfWeek.saturday
	PlatformDayOfWeek.SUNDAY -> DayOfWeek.sunday
}
