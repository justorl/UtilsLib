package com.pulse.utilslib.paper.item

import com.pulse.utilslib.paper.plugin.PluginContext
import com.pulse.utilslib.paper.extensions.onItemUse
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import kotlin.collections.find

object ItemRegistry {
    val ID = NamespacedKey(PluginContext.plugin, "item_id")
    val items = mutableListOf<CustomItem>()

    fun get(item: ItemStack): CustomItem? = items.find { it.isItem(item) }

    init {
        onItemUse { player, item ->
            val customItem = get(item) ?: return@onItemUse
            customItem.onRightClick?.invoke(player)
        }
    }
}