package com.pulse.utilslib.core.extension

val Throwable.errorSequence: Sequence<Throwable>
    get() = sequence {
        var error: Throwable = this@errorSequence
        while (true) {
            this.yield(error)
            error = error.cause ?: break
        }
    }

inline fun <T> ignoreErrors(block: () -> T?) =
    try { block() } catch (_: Exception) { null }

inline fun <T> tryOrDefault(default: T, block: () -> T): T =
    try { block() } catch (_: Exception) { default }