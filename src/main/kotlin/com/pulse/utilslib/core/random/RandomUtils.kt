package com.pulse.utilslib.core.random

import kotlin.random.Random

fun randomString(length: Int, chars: String = "abcdefghijklmnopqrstuvwxyz0123456789"): String =
    buildString(length) {
        repeat(length) {
            append(chars[Random.nextInt(chars.length)])
        }
    }

fun randomChance(chance: Double): Boolean =
    Random.nextDouble() * 100 <= chance

fun <T> randomWeighted(vararg items: Pair<T, Double>): T {
    val total = items.sumOf { it.second }

    var r = Random.nextDouble(total)
    for ((item, weight) in items) {
        r -= weight
        if (r <= 0.0) return item
    }

    return items.last().first
}