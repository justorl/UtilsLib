package com.pulse.utilslib.core.extension

fun <T> List<T>.chunkedBySize(size: Int): List<List<T>> =
    this.chunked(size)

fun <T> List<T>.shuffledCopy(): List<T> =
    this.shuffled()

fun <T> List<T>.randomOrNull(): T? =
    if (isEmpty()) null else random()

