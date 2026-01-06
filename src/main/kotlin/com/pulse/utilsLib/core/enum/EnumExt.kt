package com.pulse.utilsLib.core.enum

import kotlin.enums.EnumEntries
import kotlin.enums.enumEntries

inline operator fun <reified T : Enum<T>> T.inc(): T {
    val entries = enumEntries<T>()
    val i = ordinal + 1
    return if (i == entries.size) entries[0] else entries[i]
}

inline operator fun <reified T : Enum<T>> T.dec(): T {
    val entries = enumEntries<T>()
    val i = ordinal - 1
    return if (i < 0) entries[entries.size - 1] else entries[i]
}

inline fun <reified T : Enum<T>> T.next(
    step: Int = 1,
    cycle: Boolean = true,
    entries: EnumEntries<T> = enumEntries()
): T {
    val size = entries.size
    val next = ordinal + step

    return if (cycle) {
        entries[((next % size) + size) % size]
    } else {
        entries[next.coerceIn(0, size - 1)]
    }
}