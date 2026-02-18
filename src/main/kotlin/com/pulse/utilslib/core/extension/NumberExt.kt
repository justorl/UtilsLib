package com.pulse.utilslib.core.extension

fun Int.isBetween(min: Int, max: Int): Boolean = this in min..max

fun Int.clamp(min: Int, max: Int): Int = coerceIn(min, max)

fun IntRange.random(): Int = (start..endInclusive).random()

fun Array<Double?>.toDoubleArray(default: Double = 0.0): DoubleArray {
    return map { it ?: default }.toDoubleArray()
}

fun Array<Int?>.toIntArray(default: Int = 0): IntArray {
    return map { it ?: default }.toIntArray()
}

fun Array<Float?>.toFloatArray(default: Float = 0f): FloatArray {
    return map { it ?: default }.toFloatArray()
}

fun Array<Boolean?>.toBooleanArray(default: Boolean = false): BooleanArray {
    return map { it ?: default }.toBooleanArray()
}

fun Array<Long?>.toLongArray(default: Long = 0L): LongArray {
    return map { it ?: default }.toLongArray()
}

fun Array<Short?>.toShortArray(default: Short = 0): ShortArray {
    return map { it ?: default }.toShortArray()
}

fun Array<Char?>.toCharArray(default: Char = 0.toChar()): CharArray {
    return map { it ?: default }.toCharArray()
}

fun Array<Byte?>.toByteArray(default: Byte = 0.toByte()): ByteArray {
    return map { it ?: default }.toByteArray()
}