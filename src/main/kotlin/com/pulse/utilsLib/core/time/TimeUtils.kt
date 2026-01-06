package com.pulse.utilsLib.core.time

fun Long.duration(max: Int = 2, default: String = "0 сек", ru: Boolean = true): String {
    if (this <= 0) return default

    var remaining = this
    var count = 0

    return buildString {
        for (unit in TimeUnits.entries) {
            if (remaining >= unit.seconds) {
                append(remaining / unit.seconds)
                append(" ")
                append(if (ru) unit.ru else unit.en)

                remaining %= unit.seconds
                count++

                if (count >= max) break
                append(" ")
            }
        }
    }.ifBlank { default }
}