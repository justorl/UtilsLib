package com.pulse.utilslib.paper.plugin

import com.pulse.nexoforge.NexoForge
import com.pulse.utilslib.nexoforge.NexoItemsScanner
import com.pulse.utilslib.paper.command.CommandScanner
import com.pulse.utilslib.paper.extension.hasCommandApi
import com.pulse.utilslib.paper.extension.hasFoliaLib
import com.pulse.utilslib.paper.extension.hasLuckPermsApi
import com.pulse.utilslib.paper.extension.hasNexoForge
import com.pulse.utilslib.paper.extension.hasScoreboardLib
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
        }

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

        if (hasNexoForge()) {
            verboseLog("Initializing NexoForge step 1/2")

            PluginContext.nexoForge =
                NexoForge(this)

            verboseLog("Initializing NexoForge step 2/2")
            PluginContext.nexoForge.onEnable()

        } else verboseLog("NexoForge not found")

        verboseLog("Loading auto listeners..")
        ListenerScanner(this).apply {
            load("com.pulse.utilslib.paper.listener.builtin")
            load()
        }

        verboseLog("Loading auto commands..")
        CommandScanner(this)
            .load()

        verboseLog("Loading auto nexo items..")
        NexoItemsScanner(this)
            .load()

        enable()
    }

    override fun onDisable() {
        if (hasCommandApi()) CommandAPI.onDisable()
        if (hasScoreboardLib()) PluginContext.scoreboardLib.close()
        if (hasNexoForge()) PluginContext.nexoForge.onDisable()

        disable()
    }

    private fun verboseLog(message: String) {
        if (verboseOutput) logger.info("[$id - UtilsLib] $message")
    }
}
