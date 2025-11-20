package com.pulse.utilsLib.general

enum class TimeUnits(val prefix: String, val seconds: Long) {
    YEAR("г", 365L * 86400L),
    MONTH("мес", 30L * 86400L),
    DAY("дн", 86400L),
    HOUR("ч", 3600L),
    MINUTE("мин", 60L),
    SECOND("сек", 1L)
}