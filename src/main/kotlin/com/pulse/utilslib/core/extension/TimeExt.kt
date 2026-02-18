package com.pulse.utilslib.core.extension

import com.pulse.utilslib.core.time.TimeUnits
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

fun LocalDateTime.format(pattern: String): String =
    this.format(DateTimeFormatter.ofPattern(pattern))

fun nowISO(): String = LocalDateTime.now().format("yyyy-MM-dd'T'HH:mm:ss")

inline val Int.seconds2millis: Long
    get() = TimeUnit.SECONDS.toMillis(toLong())

inline val Int.minutes2millis: Long
    get() = TimeUnit.MINUTES.toMillis(toLong())

inline val Int.hours2millis: Long
    get() = TimeUnit.HOURS.toMillis(toLong())

inline val Int.days2millis: Long
    get() = TimeUnit.DAYS.toMillis(toLong())

inline val Long.passedTime: Long
    get() = System.currentTimeMillis() - this

fun Date.toCalendar(): Calendar =
    Calendar.getInstance().also { it.time = this }

fun Long.duration(max: Int = 2, default: String = "0 сек", ru: Boolean = true): String {
    if (this <= 0) return default

    var remaining = this
    var count = 0

    return buildString {
        for (unit in TimeUnits.entries) {
            if (remaining >= unit.seconds) {
                if (count > 0) append(" ")

                append(remaining / unit.seconds)
                append(" ")
                append(if (ru) unit.ru else unit.en)

                remaining %= unit.seconds
                count++

                if (count >= max) break
            }
        }
    }.ifBlank { default }
}
