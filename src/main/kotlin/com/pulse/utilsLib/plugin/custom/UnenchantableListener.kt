package com.pulse.utilsLib.plugin.custom

import com.pulse.utilsLib.plugin.ListenerSetup
import com.pulse.utilsLib.plugin.bukkit.isUnenchantable
import org.bukkit.event.EventHandler
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.inventory.PrepareAnvilEvent

class UnenchantableListener : ListenerSetup() {
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