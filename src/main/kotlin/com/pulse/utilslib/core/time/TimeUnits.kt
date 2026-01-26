package com.pulse.utilslib.core.time

enum class TimeUnits(
    val ru: String,
    val en: String,
    val seconds: Long
) {
    MILLENNIUM("тыс.л", "mil", 1000L * 365 * 86400),
    CENTURY("в.", "c", 100L * 365 * 86400),
    YEAR("г.", "y", 365L * 86400),
    MONTH("мес.", "mo", 30L * 86400),
    DAY("д.", "d", 86400L),
    HOUR("ч.", "h", 3600L),
    MINUTE("мин.", "min", 60L),
    SECOND("с.", "s", 1L)
}
