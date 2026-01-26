package com.pulse.utilslib.paper.item

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

class CustomItemDSL(private val id: String) {
    var defaultItem: ItemStack? = null
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
        val item = defaultItem ?: throw IllegalStateException("defaultItem must be set before calling build()")
        
        return CustomItem(
            id,
            item,
            recipe,
            onRightClick
        )
    }
}