package com.pulse.utilslib.core.extension

import java.io.Closeable

inline fun <R> Process.use(block: (Process) -> R) =
    makeUse(block) { destroy() }

inline fun <R> Process.use(block: (Process) -> R, error: (Throwable) -> R) =
    makeUse(block, error) { destroy() }

inline fun <reified T : Closeable?> T.closeQuietly() {
    try {
        this?.close()
    } catch (e: Throwable) {
    }
}

inline fun <T : Closeable?, R> T.use(block: (T) -> R, error: (Throwable) -> R): R {
    return try {
        use(block)
    } catch (e: Throwable) {
        error(e)
    }
}

inline fun <T, R> T.makeUse(block: (T) -> R, closeable: Closeable): R {
    return closeable.use { block.invoke(this) }
}

inline fun <T, R> T.makeUse(
    block: (T) -> R,
    error: (Throwable) -> R,
    closeable: Closeable
): R {
    return closeable.use({ block.invoke(this) }, error)
}