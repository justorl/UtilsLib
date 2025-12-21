package com.pulse.utilsLib.plugin

import com.pulse.utilsLib.plugin.bukkit.*
import com.tcoded.folialib.FoliaLib
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary
import org.bukkit.plugin.java.JavaPlugin

abstract class PluginSetup(id: String) : JavaPlugin() {
    @Suppress("PropertyName")
    val ID = id

    override fun onEnable() {
        if (hasCommandApi()) {
            CommandAPI.onEnable()
        }

        if (hasFoliaLib()) {
            Instance.foliaLib = FoliaLib(this)
        }

        if (hasScoreboardLib()) {
            Instance.scoreboardLib = ScoreboardLibrary.loadScoreboardLibrary(this)
        }

        ListenerLoader(this).load()
    }

    override fun onLoad() {
        Instance.plugin = this

        if (hasCommandApi()) {
            CommandAPI.onLoad(CommandAPIPaperConfig(this)
                .setNamespace(ID)
                .verboseOutput(true)
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