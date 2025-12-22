package com.pulse.utilsLib.plugins.bukkit.custom

import com.pulse.utilsLib.plugins.bukkit.ListenerSetup
import com.pulse.utilsLib.plugins.bukkit.utils.craftsLeft
import com.pulse.utilsLib.plugins.bukkit.utils.editCrafts
import com.pulse.utilsLib.plugins.bukkit.utils.isCraftable
import com.pulse.utilsLib.plugins.bukkit.utils.isMaxCraftable
import com.pulse.utilsLib.plugins.bukkit.items.CustomItem
import com.pulse.utilsLib.plugins.bukkit.items.CustomItemRegistry
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.ItemStack

class CraftsListener : ListenerSetup() {
    var postCraftAction: ((player: Player, item: ItemStack, customItem: CustomItem, craftsLeft: Int) -> Unit)? = null

    @EventHandler
    fun onCraft(e: CraftItemEvent) {
        val player = e.whoClicked as Player
        val item = e.recipe.result

        if (!item.isCraftable()) {
            e.inventory.result = ItemStack(Material.AIR)
            e.isCancelled = true
        } else if (item.isMaxCraftable()) {
            item.editCrafts(-1)

            if (postCraftAction != null) {
                val customItem = CustomItemRegistry.get(item)!!
                val craftsLeft = item.craftsLeft() ?: 0
                postCraftAction?.invoke(player, item, customItem, craftsLeft)
            }
        }
    }

    @EventHandler
    fun onPrepareCraft(e: PrepareItemCraftEvent) {
        val item = e.recipe?.result ?: return

        if (!item.isCraftable()) {
            e.inventory.result = ItemStack(Material.AIR)
        }
    }
}