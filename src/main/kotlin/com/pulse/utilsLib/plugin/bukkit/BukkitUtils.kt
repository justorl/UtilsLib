package com.pulse.utilsLib.plugin.bukkit

import com.pulse.utilsLib.plugin.Instance.plugin
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import java.io.Closeable

fun isPluginEnabled(name: String): Boolean = Bukkit.getPluginManager().getPlugin(name) != null

fun addEventListener(listener: Listener): Closeable {
    plugin.server.pluginManager.registerEvents(listener, plugin)
    return Closeable {
        HandlerList.unregisterAll(listener)
    }
}