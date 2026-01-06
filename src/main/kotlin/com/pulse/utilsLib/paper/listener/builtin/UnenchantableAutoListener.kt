package com.pulse.utilsLib.paper.listener.builtin

import com.pulse.utilsLib.paper.listener.AutoListener
import com.pulse.utilsLib.paper.extensions.isUnenchantable
import org.bukkit.event.EventHandler
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.inventory.PrepareAnvilEvent

class UnenchantableAutoListener : AutoListener() {
    @EventHandler
    fun onPreEnchant(e: PrepareItemEnchantEvent) {
        val item = e.item
        if (item.isUnenchantable()) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onAnvil(e: PrepareAnvilEvent) {
        val result = e.result ?: return
        if (result.isUnenchantable() || e.inventory.firstItem?.isUnenchantable() == true) {
            e.result = null
        }
    }
}