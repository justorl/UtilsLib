package com.pulse.utilslib.paper.plugin

import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI
import com.pulse.nexoforge.NexoForge
import com.pulse.utilslib.nexoforge.NexoItemsScanner
import com.pulse.utilslib.paper.command.CommandScanner
import com.pulse.utilslib.paper.extension.hasCommandApi
import com.pulse.utilslib.paper.extension.hasCoreProtect
import com.pulse.utilslib.paper.extension.hasFoliaLib
import com.pulse.utilslib.paper.extension.hasNexoForge
import com.pulse.utilslib.paper.extension.hasScoreboardLib
import com.pulse.utilslib.paper.extension.hasUltimateAdvancementAPI
import com.pulse.utilslib.paper.listener.ListenerScanner
import com.tcoded.folialib.FoliaLib
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.coreprotect.CoreProtect
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary
import org.bukkit.plugin.java.JavaPlugin

abstract class PaperPlugin(
    val id: String,
    private val pluginOptions: PluginOptions = PluginOptions(),
) : JavaPlugin() {

    open fun load() {}
    open fun enable() {}
    open fun disable() {}

    final override fun onLoad() {
        PluginContext.plugin = this

        if (hasCommandApi()) {
            verboseLog("Initializing CommandAPI step 1/2")
            val config = CommandAPIPaperConfig(this)
            val options = pluginOptions.commandApiOptions

            if (options.id != null) config.setNamespace(options.id)
            else config.setNamespace(id)

            if (options.verboseOutput != null) config.verboseOutput(options.verboseOutput)
            if (options.silentLogs != null)  config.silentLogs(options.silentLogs)
            if (options.fallbackToLatestNMS != null) config.fallbackToLatestNMS(options.fallbackToLatestNMS)
            if (options.addSkipSenderProxy != null) for (sender in options.addSkipSenderProxy) { config.addSkipSenderProxy(sender) }
            if (options.missingExecutorImplementationMessage != null) config.missingExecutorImplementationMessage(options.missingExecutorImplementationMessage)
            if (options.dispatcherFile != null) config.dispatcherFile(options.dispatcherFile)
            if (options.enableNetworking != null) config.enableNetworking(options.enableNetworking)
            if (options.makeNetworkingExceptionsWarnings != null) config.makeNetworkingExceptionsWarnings(options.makeNetworkingExceptionsWarnings)

            CommandAPI.onLoad(config)
        }

        load()
    }

    final override fun onEnable() {
        if (hasCommandApi()) {
            verboseLog("Initializing CommandAPI step 2/2")

            CommandAPI.onEnable()

        } else verboseLog("CommandAPI not found")

        if (hasFoliaLib()) {
            verboseLog("Initializing FoliaLib step 1/1")

            PluginContext.foliaLib =
                FoliaLib(this, pluginOptions.foliaLibOptions)

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

        if (hasCoreProtect()) {
            verboseLog("Initializing Core Protect step 1/1")

            PluginContext.coreProtect =
                server.pluginManager.getPlugin("CoreProtect") as CoreProtect
        } else verboseLog("CoreProtect not found")

        if (hasUltimateAdvancementAPI()) {
            verboseLog("Initializing Ultimate Advancement API step 1/1")

            PluginContext.uAPI =
                UltimateAdvancementAPI.getInstance(this);
        }

        verboseLog("Loading auto listeners..")
        ListenerScanner(this, pluginOptions.verbose).apply {
            load("com.pulse.utilslib.paper.listener.builtin")
            load()
        }

        if (hasCommandApi()) {
            verboseLog("Loading auto commands..")
            CommandScanner(this, pluginOptions.verbose)
                .load()
        }

        if (hasNexoForge()) {
            verboseLog("Loading auto nexo items..")
            NexoItemsScanner(this, pluginOptions.verbose)
                .load()
        }

        enable()
    }

    final override fun onDisable() {
        if (hasCommandApi()) CommandAPI.onDisable()
        if (hasScoreboardLib()) PluginContext.scoreboardLib.close()

        disable()
    }

    private fun verboseLog(message: String) {
        if (pluginOptions.verbose) logger.info("[$id - UtilsLib] $message")
    }
}
