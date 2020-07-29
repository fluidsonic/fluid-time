package io.fluidsonic.time

import kotlin.native.concurrent.freeze as nativeFreeze


@Suppress("NOTHING_TO_INLINE")
internal actual inline fun <T> T.freeze() =
	nativeFreeze()
