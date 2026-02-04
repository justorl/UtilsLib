package com.pulse.utilslib.core.extension

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