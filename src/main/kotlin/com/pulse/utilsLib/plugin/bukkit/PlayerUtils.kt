package com.pulse.utilsLib.plugin.bukkit

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

val allPlayers get() = Bukkit.getOnlinePlayers()

fun Player.pull(target: Location, strength: Double = 1.0) {
    val from = this.location.toVector()
    val to = target.toVector()

    val direction = to.subtract(from).normalize()
    val vel = direction.multiply(strength)

    velocity = vel
}

fun Player.getPlayerHead(): ItemStack {
    val head = ItemStack(Material.PLAYER_HEAD)

    head.meta<SkullMeta> {
        owningPlayer = player
    }

    return head
}

fun Player.getMaxHP(): Double = player?.getAttribute(Attribute.MAX_HEALTH)?.baseValue ?: player?.health ?: 0.0