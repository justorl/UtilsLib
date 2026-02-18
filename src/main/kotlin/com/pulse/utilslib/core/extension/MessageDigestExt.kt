package com.pulse.utilslib.core.extension

import java.security.MessageDigest

fun ByteArray.md5() = digest("MD5")

fun ByteArray.sha1() = digest("SHA-1")

fun ByteArray.sha256() = digest("SHA-256")

fun ByteArray.digest(algorithm: String): String? {
    return kotlin.runCatching { MessageDigest.getInstance(algorithm) }
        .getOrNull()?.digest(this)?.hex()
}

fun ByteArray?.hex(): String? {
    this ?: return null
    return fold(StringBuilder()) { acc, byte ->
        val hexStr = Integer.toHexString(byte.toInt() and (0xFF))
        if (hexStr.length == 1) {
            acc.append('0')
        }
        acc.append(hexStr)
    }.toString()
}