package com.pulse.utilslib.core.extension

fun classExists(name: String) = runCatching { Class.forName(name) }.isSuccess

@Suppress("UNCHECKED_CAST")
fun <T> createConstructor(className: String): T? =
    runCatching {
        Class.forName(className).getConstructor().newInstance() as T
    }.getOrNull()
