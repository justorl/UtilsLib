package com.pulse.utilsLib.paper.extensions

import com.pulse.utilsLib.paper.plugin.PluginContext
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

fun ItemMeta.setUnenchantable() {
    val key = NamespacedKey(PluginContext.plugin, "unenchantable")
    persistentDataContainer.set(key, PersistentDataType.BYTE, 1)
}

fun ItemStack.isUnenchantable(): Boolean {
    val key = NamespacedKey(PluginContext.plugin, "unenchantable")
    return itemMeta?.persistentDataContainer?.has(key, PersistentDataType.BYTE) == true
}