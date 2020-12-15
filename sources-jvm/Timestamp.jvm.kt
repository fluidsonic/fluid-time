package io.fluidsonic.time

import java.util.*
import kotlinx.datetime.*


public fun Timestamp.toJavaDate(): Date =
	Date.from(toJavaInstant())
