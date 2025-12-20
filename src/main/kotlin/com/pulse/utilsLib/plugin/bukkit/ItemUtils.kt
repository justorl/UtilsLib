package com.pulse.utilsLib.plugin.bukkit

import com.pulse.utilsLib.plugin.Instance.plugin
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
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
        fun onPlayerInteract(event: org.bukkit.event.player.PlayerInteractEvent) {
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

fun Player.take(stack: ItemStack, amount: Int = 1) {
    val taking = stack.amount - amount
    if (taking <= 0) {
        inventory.setItemInMainHand(ItemStack(Material.AIR))
    } else {
        inventory.setItemInMainHand(stack.asQuantity(taking))
    }
}

var craftableMap = mutableMapOf<NamespacedKey, Byte>()

fun ItemMeta.setMaxCraftable(max: Byte) {
    val key = NamespacedKey(plugin, "max_craftable")
    persistentDataContainer.set(key, PersistentDataType.BYTE, max)
    craftableMap[key] = 0.toByte()
}

fun ItemStack.getMaxCraftable(): Byte? {
    val key = NamespacedKey(plugin, "max_craftable")
    return itemMeta?.persistentDataContainer?.get(key, PersistentDataType.BYTE)
}

fun ItemStack.editCrafts(by: Int) {
    val key = NamespacedKey(plugin, "max_craftable")
    val max = getMaxCraftable() ?: return
    val used = craftableMap[key] ?: max

    craftableMap[key] = (used + by).toByte()
}

fun ItemStack.craftsLeft(): Int? {
    val key = NamespacedKey(plugin, "max_craftable")
    val data = getMaxCraftable() ?: return null
    val map = craftableMap[key] ?: return null

    return data - map
}

fun ItemStack.isMaxCraftable(): Boolean = getMaxCraftable() != null

fun ItemStack.isCraftable(): Boolean {
    return (craftsLeft() ?: return true) > 0
}

fun ItemMeta.setUnenchantable() {
    val key = NamespacedKey(plugin, "unenchantable")
    persistentDataContainer.set(key, PersistentDataType.BYTE, 1)
}

fun ItemStack.isUnenchantable(): Boolean {
    val key = NamespacedKey(plugin, "unenchantable")
    return itemMeta?.persistentDataContainer?.has(key, PersistentDataType.BYTE) == true
}