package com.pulse.utilslib.paper.extensions

import com.pulse.utilslib.adventure.audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

val allPlayers get() = Bukkit.getOnlinePlayers()
val allAudiences get() = allPlayers.audience()

fun Player.pull(target: Location, strength: Double = 1.0) {
    val from = this.location.toVector()
    val to = target.toVector()

    val direction = to.subtract(from).normalize()
    val vel = direction.multiply(strength)

    velocity = vel
}

fun Player.getPlayerHead(): ItemStack {
    val head = ItemStack(Material.PLAYER_HEAD)

    return head.meta<SkullMeta> {
        owningPlayer = player
    }
}

val Attributable.maxHP: Double
    get() = getAttribute(Attribute.MAX_HEALTH)?.baseValue
        ?: (this as? Damageable)?.health
        ?: 0.0

fun Player.showTitle(title: Component, subtitle: Component, fadeIn: Int, stay: Int, fadeOut: Int) =
    showTitle(Title.title(title, subtitle, fadeIn, stay, fadeOut))