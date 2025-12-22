package com.pulse.utilsLib.general.utils

import kotlin.random.Random

fun randomChance(chance: Double): Boolean {
    return Random.nextDouble() * 100 <= chance
}

operator fun <T : Enum<T>> T.dec(): T {
    val values = javaClass.enumConstants
    val prev = (ordinal - 1 + values.size) % values.size
    return values[prev]
}

operator fun <T : Enum<T>> T.inc(): T {
    val values = javaClass.enumConstants
    val next = (ordinal + 1) % values.size
    return values[next]
}

fun <T : Enum<T>> T.next(
    step: Int = 1,
    cycle: Boolean = true,
    predicate: (T) -> Boolean = { true }
): T {
    val values = javaClass.enumConstants.filter(predicate)
    val nextIndex = values.indexOf(this) + step
    return if (cycle) values[nextIndex % values.size] else values.getOrElse(nextIndex) { values.last() }
}

fun classExists(name: String) = runCatching { Class.forName(name) }.isSuccess

@Suppress("UNCHECKED_CAST")
fun <T> createConstructor(className: String): T? =
    runCatching { Class.forName(className).getConstructor().newInstance() as T }.getOrNull()
