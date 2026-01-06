package com.pulse.utilsLib.paper.extensions.adventure

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.title.Title
import java.time.Duration

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

fun Title.title(title: Component, subtitle: Component, fadeIn: Long, stay: Long, fadeOut: Long): Title =
    Title.title(title, subtitle, Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut)))