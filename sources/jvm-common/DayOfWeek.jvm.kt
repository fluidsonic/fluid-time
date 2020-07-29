package io.fluidsonic.time


public fun DayOfWeek.toPlatform(): PlatformDayOfWeek = when (this) {
	DayOfWeek.monday -> PlatformDayOfWeek.MONDAY
	DayOfWeek.tuesday -> PlatformDayOfWeek.TUESDAY
	DayOfWeek.wednesday -> PlatformDayOfWeek.WEDNESDAY
	DayOfWeek.thursday -> PlatformDayOfWeek.THURSDAY
	DayOfWeek.friday -> PlatformDayOfWeek.FRIDAY
	DayOfWeek.saturday -> PlatformDayOfWeek.SATURDAY
	DayOfWeek.sunday -> PlatformDayOfWeek.SUNDAY
}


public fun PlatformDayOfWeek.toCommon(): DayOfWeek = when (this) {
	PlatformDayOfWeek.MONDAY -> DayOfWeek.monday
	PlatformDayOfWeek.TUESDAY -> DayOfWeek.tuesday
	PlatformDayOfWeek.WEDNESDAY -> DayOfWeek.wednesday
	PlatformDayOfWeek.THURSDAY -> DayOfWeek.thursday
	PlatformDayOfWeek.FRIDAY -> DayOfWeek.friday
	PlatformDayOfWeek.SATURDAY -> DayOfWeek.saturday
	PlatformDayOfWeek.SUNDAY -> DayOfWeek.sunday
}
