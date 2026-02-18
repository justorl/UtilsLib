package com.pulse.utilslib.adventure.extension

import com.pulse.utilslib.adventure.Serializers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.ComponentSerializer

fun Component.serialize(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): (Component) -> String = serializer::serialize

fun Component.serializeOrNull(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): (Component?) -> String? = serializer::serializeOrNull

fun String.deserialize(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): (String) -> Component = serializer::deserialize

fun String.deserializeOrNull(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): (String?) -> Component? = serializer::deserializeOrNull