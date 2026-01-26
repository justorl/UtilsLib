package com.pulse.utilslib.paper.extensions

import com.pulse.utilslib.core.extensions.classExists
import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import java.io.Closeable

fun addEventListener(listener: Listener): Closeable {
    Bukkit.getPluginManager().registerEvents(listener, PluginContext.plugin)
    return Closeable {
        HandlerList.unregisterAll(listener)
    }
}

fun isPluginEnabled(name: String): Boolean = Bukkit.getPluginManager().getPlugin(name) != null

fun hasFoliaLib(): Boolean =
    classExists("com.tcoded.folialib.FoliaLib")

fun hasCommandApi(): Boolean =
    classExists("dev.jorel.commandapi.CommandAPI")

fun hasScoreboardLib(): Boolean =
    classExists("net.megavex.scoreboardlibrary.api.ScoreboardLibrary")

fun hasClassGraph(): Boolean =
    classExists("io.github.classgraph.ClassGraph")

fun isFolia() : Boolean =
    classExists("io.papermc.paper.threadedregions.scheduler.RegionScheduler")