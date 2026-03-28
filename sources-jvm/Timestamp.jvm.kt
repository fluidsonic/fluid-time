package io.fluidsonic.time

import java.util.*
import kotlin.time.toJavaInstant
import kotlinx.datetime.*


/** Converts this [Timestamp] to a legacy [java.util.Date][Date]. */
public fun Timestamp.toJavaDate(): Date =
	Date.from(toJavaInstant())
