package com.pulse.utilslib.core.extension

fun <A, B> Iterable<Pair<A, B>>.forEach(action: (first: A, second: B) -> Unit) {
    forEach { action(it.first, it.second) }
}

fun <A, B> Array<Pair<A, B>>.forEach(action: (first: A, second: B) -> Unit) {
    this.forEach { it: Pair<A, B> -> action(it.first, it.second) }
}