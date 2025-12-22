package com.pulse.utilsLib.plugins.bukkit.utils

import com.pulse.utilsLib.general.utils.classExists
import com.pulse.utilsLib.plugins.bukkit.Instance.plugin
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import java.io.Closeable

fun isPluginEnabled(name: String): Boolean = Bukkit.getPluginManager().getPlugin(name) != null

fun addEventListener(listener: Listener): Closeable {
    Bukkit.getPluginManager().registerEvents(listener, plugin)
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