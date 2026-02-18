package com.pulse.utilslib.core.extension

inline fun <reified T : Annotation> Class<*>.declaredMethodsAnnotateBy() = declaredMethods
    .mapNotNull {
        val annotation = it.getAnnotation(T::class.java)
            ?: return@mapNotNull null

        return@mapNotNull Pair(it, annotation)
    }

inline fun <reified T : Annotation> Class<*>.declaredFieldsAnnotateBy() = declaredFields
    .mapNotNull {
        val annotation = it.getAnnotation(T::class.java)
            ?: return@mapNotNull null

        return@mapNotNull Pair(it, annotation)
    }