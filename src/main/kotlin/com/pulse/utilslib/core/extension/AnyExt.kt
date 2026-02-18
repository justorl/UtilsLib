package com.pulse.utilslib.core.extension

inline fun <T> T?.ifNull(block: () -> Unit) {
    if (this == null) block()
}
inline fun <T> T?.ifNotNull(block: (T) -> Unit): T? {
    this?.let(block)
    return this
}

inline fun <T, R> T?.mapOr(default: R, block: (T) -> R): R =
    this?.let(block) ?: default

inline fun <T> repeatTimes(times: Int, block: (Int) -> T): T {
    var result: T? = null
    repeat(times) { result = block(it) }
    return result!!
}

inline fun <T> T.tap(block: (T) -> Unit): T {
    block(this)
    return this
}

inline fun <T> repeatCollect(n: Int, block: (Int) -> T): List<T> =
    List(n) { block(it) }