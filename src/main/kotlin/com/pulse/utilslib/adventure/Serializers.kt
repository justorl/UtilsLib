package com.pulse.utilslib.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.ComponentSerializer
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

enum class Serializers(val serializer: ComponentSerializer<Component, *, String>) {
    PLAIN(PlainTextComponentSerializer.plainText()),
    LEGACY(LegacyComponentSerializer.legacyAmpersand()),
    MINI(MiniMessage.miniMessage()),
    JSON(JSONComponentSerializer.json()),
    GSON(GsonComponentSerializer.gson());

    operator fun invoke(): ComponentSerializer<Component, *, String> = serializer
}