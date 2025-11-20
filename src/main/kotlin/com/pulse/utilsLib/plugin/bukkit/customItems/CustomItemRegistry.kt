package com.pulse.utilsLib.plugin.bukkit.customItems

import com.pulse.utilsLib.plugin.Instance.plugin
import com.pulse.utilsLib.plugin.bukkit.onItemUse
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import kotlin.collections.find

object CustomItemRegistry {
    val ID get() = NamespacedKey(plugin, "item_id")
    val items = mutableListOf<CustomItem>()

    fun get(item: ItemStack): CustomItem? = items.find { it.isItem(item) }

    init {
        onItemUse { player, item ->
            val customItem = get(item) ?: return@onItemUse
            customItem.onRightClick?.invoke(player)
        }
    }
}