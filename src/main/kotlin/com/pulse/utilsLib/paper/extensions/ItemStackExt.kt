package com.pulse.utilslib.paper.extensions

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta
import java.io.Closeable

inline fun <reified T : ItemMeta> ItemStack.meta(block: T.() -> Unit): ItemStack {
    val meta = itemMeta as T
    meta.block()
    itemMeta = meta
    return this
}

fun onItemUse(listener: (Player, ItemStack) -> Unit): Closeable {
    return addEventListener(object : Listener {
        @EventHandler
        fun onPlayerInteract(event: PlayerInteractEvent) {
            if (event.action != Action.RIGHT_CLICK_AIR && event.action != Action.RIGHT_CLICK_BLOCK) return
            if (event.action == Action.RIGHT_CLICK_BLOCK && !(event.clickedBlock?.type?.isInteractable == false || event.player.isSneaking)) return
            listener(event.player, event.item ?: return)
        }
    })
}

fun ItemStack.isSame(item: ItemStack): Boolean =
    this.generic() == item.generic()

fun ItemStack.generic(): ItemStack =
    ignoreEnchants().ignoreDurability()

fun ItemStack.ignoreEnchants(): ItemStack {
    if (itemMeta !is Damageable) return this

    return clone().meta<Damageable> {
        removeEnchantments()
    }
}

fun ItemStack.ignoreDurability(): ItemStack {
    if (itemMeta !is Damageable) return this

    return clone().meta<Damageable> {
        damage = 0
    }
}

fun Player.takeFromMainHand(amount: Int = 1) {
    val stack = inventory.itemInMainHand
    val taking = stack.amount - amount
    if (taking <= 0) {
        inventory.setItemInMainHand(ItemStack(Material.AIR))
    } else {
        inventory.setItemInMainHand(stack.asQuantity(taking))
    }
}

fun Player.takeFromOffHand(amount: Int = 1) {
    val stack = inventory.itemInOffHand
    val taking = stack.amount - amount
    if (taking <= 0) {
        inventory.setItemInOffHand(ItemStack(Material.AIR))
    } else {
        inventory.setItemInOffHand(stack.asQuantity(taking))
    }
}