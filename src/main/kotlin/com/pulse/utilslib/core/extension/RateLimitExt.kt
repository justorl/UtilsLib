package com.pulse.utilslib.core.extension

import java.util.concurrent.ConcurrentHashMap

val rateLimits = ConcurrentHashMap<String, Long>()

fun rateLimit(key: String, duration: Long): Boolean {
    val now = System.currentTimeMillis()
    val newEnd = now + duration

    val old = rateLimits.putIfAbsent(key, newEnd)
    if (old == null) return true

    if (now >= old) {
        return rateLimits.replace(key, old, newEnd)
    }

    return false
}