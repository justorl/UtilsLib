package com.pulse.utilsLib.plugins.bukkit.items

import com.pulse.utilsLib.plugins.bukkit.Instance.plugin
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.persistence.PersistentDataType

// Original code by Cymaera SkyTorch, this is modified version
class CustomItem(
    val id: String,
    defaultItem: ItemStack,
    recipe: ((NamespacedKey, ItemStack) -> Recipe)? = null,
    val onRightClick: ((player: Player) -> Unit)? = null
) {
    val item = defaultItem.apply { attach(this) }

    var recipe: Recipe? = null
        private set

    init {
        if (recipe != null) {
            val key = NamespacedKey(plugin, id)
            val r = recipe(key, item)
            Bukkit.addRecipe(r)
            this.recipe = r
        }
    }

    fun attach(item: ItemStack) {
        val itemMeta = item.itemMeta ?: throw Exception("ItemMeta is null")
        itemMeta.persistentDataContainer.set(CustomItemRegistry.ID, PersistentDataType.STRING, id)
        item.itemMeta = itemMeta
    }

    fun isItem(item: ItemStack): Boolean {
        return item.itemMeta?.persistentDataContainer?.get(CustomItemRegistry.ID, PersistentDataType.STRING) == id
    }
}