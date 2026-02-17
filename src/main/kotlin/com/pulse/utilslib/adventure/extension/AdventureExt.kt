package com.pulse.utilslib.adventure.extension

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.ComponentSerializer
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.title.Title
import java.time.Duration

// todo move this method?
fun org.bukkit.Sound.sound(
    source: Sound.Source = Sound.Source.MASTER,
    volume: Float = 1f,
    pitch: Float = 1f
) = Sound.sound(
    key(),
    source,
    volume,
    pitch
)

fun String.sound(
    source: Sound.Source = Sound.Source.MASTER,
    volume: Float = 1f,
    pitch: Float = 1f
) = Sound.sound(
    key(),
    source,
    volume,
    pitch
)

fun String.key() = Key.key(this)

fun String.cmp(vararg placeholders: Pair<String, Component>): Component {
    val resolver = TagResolver.resolver(
        placeholders.map { (key, value) ->
            Placeholder.component(key, value)
        }
    )

    return MiniMessage.miniMessage().deserialize(this, resolver)
}

fun String.cmp(color: Boolean = false) =
    if (color)
        MiniMessage.miniMessage().deserialize(this)
    else
        Component.text(this)

fun Any?.cmp() = Component.text(this.toString())

fun Collection<Audience>.audience() = Audience.audience(this)

fun createTitle(title: Component, subtitle: Component, fadeIn: Long, stay: Long, fadeOut: Long): Title =
    Title.title(title, subtitle, Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut)))

// serializers & extensions for them were made by alexthegoood in his paper-language-kotlin
enum class Serializers(val serializer: ComponentSerializer<Component, *, String>) {
    PLAIN(PlainTextComponentSerializer.plainText()),
    LEGACY(LegacyComponentSerializer.legacyAmpersand()),
    MINI(MiniMessage.miniMessage()),
    JSON(JSONComponentSerializer.json()),
    GSON(GsonComponentSerializer.gson());

    operator fun invoke(): ComponentSerializer<Component, *, String> = serializer
}

fun Component.serialize(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): String = serializer.serialize(this)

fun String.deserialize(
    serializer: ComponentSerializer<Component, *, String> = Serializers.PLAIN()
): Component = serializer.deserialize(this)

fun Component.replaceLiteral(
    literal: String,
    replace: String
): Component = replaceText { builder -> builder.matchLiteral(literal).replacement(replace) }

fun Component.replaceLiteral(
    literal: String,
    replace: ComponentLike
): Component = replaceText { builder -> builder.matchLiteral(literal).replacement(replace) }