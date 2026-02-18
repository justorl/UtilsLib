package com.pulse.utilslib.core.extension

import java.io.File

inline fun String.toFile() = File(this)

fun File.createParentIfNotExists(): Boolean {
    val parent = parentFile
    if (!parent.exists()) {
        return parent.mkdirs()
    }
    return true
}

fun File.createFileIfNotExists(): Boolean {
    if (!exists()) {
        return createNewFile()
    }
    return true

}

fun File.createDirIfNotExists(): Boolean {
    if (!exists()) {
        return mkdir()
    }
    return true

}

fun File.ensureDir(): File {
    if (isDirectory) {
        return this
    }
    if (exists() && !delete()) {
        throw RuntimeException("Can't delete file $path")
    }
    if (!mkdir()) {
        throw RuntimeException("Can't create dir $path")
    }
    return this
}

fun File.deleteQuietly() = kotlin.runCatching { delete() }.isSuccess

operator fun File.div(child: String) = File(this, child)

fun File.children(): Array<File> = listFiles() ?: emptyArray()