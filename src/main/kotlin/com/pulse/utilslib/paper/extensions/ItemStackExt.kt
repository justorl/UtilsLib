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
            if (event.action == Action.RIGHT_CLICK_BLOCK) {
                val block = event.clickedBlock
                if (block?.type?.isInteractable == true && !event.player.isSneaking) return
            }
            
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

private fun Player.takeFromHand(
    getItem: () -> ItemStack,
    setItem: (ItemStack) -> Unit,
    amount: Int
) {
    val stack = getItem()
    val remaining = stack.amount - amount
    if (remaining <= 0) {
        setItem(ItemStack(Material.AIR))
    } else {
        setItem(stack.asQuantity(remaining))
    }
}

fun Player.takeFromMainHand(amount: Int = 1) {
    takeFromHand(
        getItem = { inventory.itemInMainHand },
        setItem = { inventory.setItemInMainHand(it) },
        amount = amount
    )
}

fun Player.takeFromOffHand(amount: Int = 1) {
    takeFromHand(
        getItem = { inventory.itemInOffHand },
        setItem = { inventory.setItemInOffHand(it) },
        amount = amount
    )
}