package com.pulse.utilsLib.plugins.bukkit.items

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

class CustomItemBuilder(private val id: String) {
    lateinit var defaultItem: ItemStack
    var recipe: ((NamespacedKey, ItemStack) -> Recipe)? = null
    private var onRightClick: ((Player) -> Unit)? = null

    companion object {
        fun customItem(id: String, block: CustomItemBuilder.() -> Unit) {
            val builder = CustomItemBuilder(id).apply(block)
            val item = builder.build()
            CustomItemRegistry.items += item
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