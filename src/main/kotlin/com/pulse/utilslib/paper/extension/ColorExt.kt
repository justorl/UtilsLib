package com.pulse.utilslib.paper.extension

import org.bukkit.Color

fun String.color(alpha: Int = 255): Color {
    val clean = this.removePrefix("#")

    val r = clean.substring(0, 2).toInt(16)
    val g = clean.substring(2, 4).toInt(16)
    val b = clean.substring(4, 6).toInt(16)

    return Color.fromARGB(alpha, r, g, b)
}

fun Color.toHex(lowercase: Boolean = false, hashtag: Boolean = true): String {
    val r = red
        .coerceIn(0, 255).toString(16).padStart(2, '0')
    val g = green
        .coerceIn(0, 255).toString(16).padStart(2, '0')
    val b = blue
        .coerceIn(0, 255).toString(16).padStart(2, '0')

    var hex = "$r$g$b"

    hex = if (lowercase) hex.lowercase() else hex.uppercase()
    if (hashtag) hex = "#$hex"

    return hex
}