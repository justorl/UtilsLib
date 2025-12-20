package com.pulse.utilsLib.general

import java.util.concurrent.ConcurrentHashMap

val rateLimits = ConcurrentHashMap<String, Long>()

fun rateLimit(key: String, duration: Long): Boolean {
    val now = System.currentTimeMillis()

    rateLimits.entries.removeIf { it.value < now }

    val endTime = rateLimits[key]
    if (endTime != null && now < endTime) return true

    rateLimits[key] = now + duration
    return false
}

fun Long.duration(max: Int = 2, default: String = "0 сек", ru: Boolean = true): String {
    if (this <= 0) return default

    var remaining = this
    var count = 0

    return buildString {
        for (unit in TimeUnits.entries) {
            if (remaining >= unit.seconds) {
                append(remaining / unit.seconds)
                    .append(" ")
                   .append(if (ru) unit.ru else unit.en)

                remaining %= unit.seconds
                count++

                if (count >= max) break
                append(" ")
            }
        }
    }.ifBlank { default }
}