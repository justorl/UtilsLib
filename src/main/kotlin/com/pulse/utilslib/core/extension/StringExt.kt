package com.pulse.utilslib.core.extension

inline fun String?.ifNullOrEmpty(block: () -> String) =
    if (isNullOrEmpty()) block() else this

fun String?.orDefault(default: String): String =
    if (isNullOrEmpty()) default else this

fun String.toSnakeCase(): String =
    this.replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase()

fun String.toCamelCase(): String =
    split("_", " ").joinToString("") { it.replaceFirstChar { c -> c.lowercase() } }

fun String.isPalindrome(): Boolean =
    this == this.reversed()