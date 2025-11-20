package com.pulse.utilsLib.general

import org.bukkit.Color
import kotlin.random.Random

fun randomChance(chance: Double): Boolean {
    return Random.nextDouble() * 100 <= chance
}

operator fun <T : Enum<T>> T.inc(): T {
    val values = javaClass.enumConstants
    val next = (ordinal + 1) % values.size
    return values[next]
}

fun String.color(alpha: Int = 255): Color {
    val clean = this.removePrefix("#")
    val r = clean.substring(0, 2).toInt(16)
    val g = clean.substring(2, 4).toInt(16)
    val b = clean.substring(4, 6).toInt(16)

    return Color.fromARGB(alpha, r, g, b)
}

fun Color.toHex(lowercase: Boolean = false): String {
    val r = red
        .coerceIn(0, 255).toString(16).padStart(2, '0')
    val g = green
        .coerceIn(0, 255).toString(16).padStart(2, '0')
    val b = blue
        .coerceIn(0, 255).toString(16).padStart(2, '0')

    return if (lowercase) "#${r}${g}${b}".lowercase() else "#${r}${g}${b}".uppercase()
}

fun hasFoliaLib(): Boolean =
    classExists("com.tcoded.folialib.FoliaLib")

fun hasCommandApi(): Boolean =
    classExists("dev.jorel.commandapi.CommandAPI")

fun hasScoreboardLib(): Boolean =
    classExists("net.megavex.scoreboardlibrary.api.ScoreboardLibrary")

private fun classExists(name: String): Boolean {
    return try {
        Class.forName(name)
        true
    } catch (_: ClassNotFoundException) {
        false
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> createConstructor(className: String): T? {
    return try {
        Class.forName(className)
            .getConstructor()
            .newInstance() as T
    } catch (e: ClassNotFoundException) {
        null
    }
}