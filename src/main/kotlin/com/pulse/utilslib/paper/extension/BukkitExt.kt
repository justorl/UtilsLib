package com.pulse.utilslib.paper.extension

import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.Server
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import java.io.Closeable

fun Server.plugin(
    unit: PluginManager.() -> Unit
) = pluginManager.also { with(pluginManager, unit) }

fun PluginManager.events(
    plugin: Plugin,
    vararg listeners: Listener
) { listeners.forEach { registerEvents(it, plugin) } }

fun Server.events(
    plugin: Plugin,
    vararg listeners: Listener
) { pluginManager.events(plugin, *listeners) }

fun Plugin.events(
    vararg listeners: Listener
) { server.events(this, *listeners) }

fun addEventListener(listener: Listener): Closeable {
    Bukkit.getPluginManager().registerEvents(listener, PluginContext.plugin)
    return Closeable {
        HandlerList.unregisterAll(listener)
    }
}

fun String.nKey() =
    NamespacedKey.fromString(this)!!

fun String.nKey(namespace: String) =
    NamespacedKey.fromString("$namespace:$this")!!

fun isPluginEnabled(name: String): Boolean =
    Bukkit.getPluginManager().getPlugin(name) != null