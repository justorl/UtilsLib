package com.pulse.utilsLib.paper.item

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

class CustomItemDSL(private val id: String) {
    lateinit var defaultItem: ItemStack
    var recipe: ((NamespacedKey, ItemStack) -> Recipe)? = null
    private var onRightClick: ((Player) -> Unit)? = null

    companion object {
        fun customItem(id: String, block: CustomItemDSL.() -> Unit) {
            val builder = CustomItemDSL(id).apply(block)
            val item = builder.build()
            ItemRegistry.items += item
        }
    }

    fun onRightClick(block: (Player) -> Unit) {
        onRightClick = block
    }

    fun build(): CustomItem {
        return CustomItem(
            id,
            defaultItem,
            recipe,
            onRightClick
        )
    }
}