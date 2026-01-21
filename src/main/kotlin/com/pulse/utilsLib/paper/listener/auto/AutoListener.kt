package com.pulse.utilslib.paper.listener.auto

import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.Bukkit
import org.bukkit.event.Listener

abstract class AutoListener(
    val autoRegister: Boolean = true,
) : Listener {
    fun register() =
        Bukkit.getPluginManager().registerEvents(this, PluginContext.plugin)
}