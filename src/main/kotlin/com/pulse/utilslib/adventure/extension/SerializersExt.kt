package com.pulse.utilslib.adventure.extension

import com.pulse.utilslib.adventure.Serializers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.ComponentSerializer

fun Component.serialize(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): String = serializer.serialize(this)

fun Component.serializeOrNull(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): String? = serializer.serializeOrNull(this)

fun String.deserialize(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): Component = serializer.deserialize(this)

fun String.deserializeOrNull(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): Component? = serializer.deserializeOrNull(this)