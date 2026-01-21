package com.pulse.utilslib.paper.extensions

import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

var craftableMap = mutableMapOf<NamespacedKey, Byte>()

fun ItemMeta.setMaxCraftable(max: Byte) {
    val key = NamespacedKey(PluginContext.plugin, "max_craftable")
    persistentDataContainer.set(key, PersistentDataType.BYTE, max)
    craftableMap[key] = 0.toByte()
}

fun ItemStack.getMaxCraftable(): Byte? {
    val key = NamespacedKey(PluginContext.plugin, "max_craftable")
    return itemMeta?.persistentDataContainer?.get(key, PersistentDataType.BYTE)
}

fun ItemStack.editCrafts(by: Int) {
    val key = NamespacedKey(PluginContext.plugin, "max_craftable")
    val max = getMaxCraftable() ?: return
    val used = craftableMap[key] ?: max

    craftableMap[key] = (used + by).toByte()
}

fun ItemStack.craftsLeft(): Int? {
    val key = NamespacedKey(PluginContext.plugin, "max_craftable")
    val data = getMaxCraftable() ?: return null
    val map = craftableMap[key] ?: return null

    return data - map
}

fun ItemStack.isMaxCraftable(): Boolean = getMaxCraftable() != null

fun ItemStack.isCraftable(): Boolean {
    return (craftsLeft() ?: return true) > 0
}