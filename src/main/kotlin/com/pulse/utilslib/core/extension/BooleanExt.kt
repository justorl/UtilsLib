package com.pulse.utilslib.core.extension

inline fun <T> Boolean.map(trueValue: T, falseValue: T): T =
    if (this) trueValue else falseValue

inline fun Boolean?.ifTrue(block: () -> Unit) {
    if (this == true) block()
}

inline fun Boolean?.ifFalse(block: () -> Unit) {
    if (this == false) block()
}

inline fun Boolean.onTrue(action: () -> Unit): Boolean {
    if (this) {
        action()
    }
    return this
}

inline fun Boolean.onFalse(action: () -> Unit): Boolean {
    if (!this) {
        action()
    }
    return this
}

fun Boolean?.orFalse(): Boolean = this ?: false

inline fun Boolean.thenElse(ifTrue: () -> Unit, ifFalse: () -> Unit) {
    if (this) ifTrue() else ifFalse()
}

fun Boolean.toInt() = if (this) 1 else 0