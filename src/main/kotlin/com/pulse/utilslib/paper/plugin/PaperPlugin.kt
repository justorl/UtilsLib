package com.pulse.utilslib.paper.plugin

import com.pulse.utilslib.paper.command.CommandScanner
import com.pulse.utilslib.paper.extensions.hasCommandApi
import com.pulse.utilslib.paper.extensions.hasFoliaLib
import com.pulse.utilslib.paper.extensions.hasScoreboardLib
import com.pulse.utilslib.paper.listener.ListenerScanner
import com.tcoded.folialib.FoliaLib
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary
import org.bukkit.plugin.java.JavaPlugin

abstract class PaperPlugin(
    val id: String,
    val verboseOutput: Boolean = false
) : JavaPlugin() {

    open fun load() {}
    open fun enable() {}
    open fun disable() {}

    override fun onLoad() {
        PluginContext.plugin = this

        if (hasCommandApi()) {
            verboseLog("Initializing CommandAPI step 1/2")
            CommandAPI.onLoad(
                CommandAPIPaperConfig(this)
                    .setNamespace(id)
                    .verboseOutput(verboseOutput)
            )
        } else

        load()
    }

    override fun onEnable() {
        if (hasCommandApi()) {
            verboseLog("Initializing CommandAPI step 2/2")

            CommandAPI.onEnable()

        } else verboseLog("CommandAPI not found")

        if (hasFoliaLib()) {
            verboseLog("Initializing FoliaLib step 1/1")

            PluginContext.foliaLib =
                FoliaLib(this)

        } else verboseLog("FoliaLib not found")

        if (hasScoreboardLib()) {
            verboseLog("Initializing ScoreboardLibrary step 1/1")

            PluginContext.scoreboardLib =
                ScoreboardLibrary.loadScoreboardLibrary(this)

        } else verboseLog("ScoreboardLib not found")

        verboseLog("Loading auto listeners..")
        ListenerScanner(this).apply {
            load("com.pulse.utilsLib.plugin.bukkit.custom")
            load()
        }

        verboseLog("Loading auto commands..")
        CommandScanner(this).load()

        enable()
    }

    override fun onDisable() {
        if (hasCommandApi()) CommandAPI.onDisable()
        if (hasScoreboardLib()) PluginContext.scoreboardLib.close()

        disable()
    }

    private fun verboseLog(message: String) {
        if (verboseOutput) logger.info("[$id - UtilsLib] $message")
    }
}
