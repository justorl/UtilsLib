package com.pulse.utilsLib.plugin.custom

import com.pulse.utilsLib.plugin.ListenerSetup
import com.pulse.utilsLib.plugin.bukkit.craftsLeft
import com.pulse.utilsLib.plugin.bukkit.editCrafts
import com.pulse.utilsLib.plugin.bukkit.isCraftable
import com.pulse.utilsLib.plugin.bukkit.isMaxCraftable
import com.pulse.utilsLib.plugin.bukkit.items.CustomItem
import com.pulse.utilsLib.plugin.bukkit.items.CustomItemRegistry
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