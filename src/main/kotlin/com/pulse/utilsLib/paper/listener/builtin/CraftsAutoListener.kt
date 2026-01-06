package com.pulse.utilsLib.paper.listener.builtin

import com.pulse.utilsLib.paper.extensions.craftsLeft
import com.pulse.utilsLib.paper.extensions.editCrafts
import com.pulse.utilsLib.paper.extensions.isCraftable
import com.pulse.utilsLib.paper.extensions.isMaxCraftable
import com.pulse.utilsLib.paper.item.CustomItem
import com.pulse.utilsLib.paper.item.ItemRegistry
import com.pulse.utilsLib.paper.listener.AutoListener
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.ItemStack

class CraftsAutoListener : AutoListener() {
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
                val customItem = ItemRegistry.get(item)!!
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