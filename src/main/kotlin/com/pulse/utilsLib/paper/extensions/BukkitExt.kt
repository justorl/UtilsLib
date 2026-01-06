package com.pulse.utilsLib.paper.extensions

import com.pulse.utilsLib.core.reflect.classExists
import com.pulse.utilsLib.paper.plugin.PluginContext
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import java.io.Closeable

fun isPluginEnabled(name: String): Boolean = Bukkit.getPluginManager().getPlugin(name) != null

fun addEventListener(listener: Listener): Closeable {
    Bukkit.getPluginManager().registerEvents(listener, PluginContext.plugin)
    return Closeable {
        HandlerList.unregisterAll(listener)
    }
}

fun hasFoliaLib(): Boolean =
    classExists("com.tcoded.folialib.FoliaLib")

fun hasCommandApi(): Boolean =
    classExists("dev.jorel.commandapi.CommandAPI")

fun hasScoreboardLib(): Boolean =
    classExists("net.megavex.scoreboardlibrary.api.ScoreboardLibrary")

fun hasClassGraph(): Boolean =
    classExists("io.github.classgraph.ClassGraph")