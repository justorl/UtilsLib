package com.pulse.utilsLib.plugins.bukkit

import com.pulse.utilsLib.plugins.bukkit.utils.hasCommandApi
import com.pulse.utilsLib.plugins.bukkit.utils.hasFoliaLib
import com.pulse.utilsLib.plugins.bukkit.utils.hasScoreboardLib
import com.pulse.utilsLib.plugins.commandapi.CommandLoader
import com.tcoded.folialib.FoliaLib
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary
import org.bukkit.plugin.java.JavaPlugin

abstract class PluginSetup(id: String, val verboseOutput: Boolean = false) : JavaPlugin() {
    @Suppress("PropertyName")
    val ID = id

    override fun onEnable() {
        if (hasCommandApi()) {
            CommandAPI.onEnable()
            if (verboseOutput) println("[$ID - UtilsLib] Loading commands..")
            CommandLoader(this).load()
        } else if (Instance.plugin.verboseOutput) println("[$ID - UtilsLib] No CommandAPI found")

        if (hasFoliaLib()) {
            Instance.foliaLib = FoliaLib(this)
        } else if (Instance.plugin.verboseOutput) println("[$ID - UtilsLib] No FoliaLib found")

        if (hasScoreboardLib()) {
            Instance.scoreboardLib = ScoreboardLibrary.loadScoreboardLibrary(this)
        } else if (Instance.plugin.verboseOutput) println("[$ID - UtilsLib] No ScoreboardLib found")

        if (verboseOutput) println("[$ID - UtilsLib] Loading listeners..")
        ListenerLoader(this).load("com.pulse.utilsLib.plugin.bukkit.custom")
        ListenerLoader(this).load()
    }

    override fun onLoad() {
        Instance.plugin = this

        if (hasCommandApi()) {
            CommandAPI.onLoad(CommandAPIPaperConfig(this)
                .setNamespace(ID)
                .verboseOutput(verboseOutput)
            )
        }
    }

    override fun onDisable() {
        if (hasCommandApi()) {
            CommandAPI.onDisable()
        }

        if (hasCommandApi()) {
            Instance.scoreboardLib.close()
        }
    }
}