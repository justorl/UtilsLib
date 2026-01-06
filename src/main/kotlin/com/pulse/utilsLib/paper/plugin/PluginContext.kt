package com.pulse.utilsLib.paper.plugin

import com.tcoded.folialib.FoliaLib
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary

object PluginContext {
    lateinit var foliaLib: FoliaLib
    lateinit var scoreboardLib: ScoreboardLibrary
    lateinit var plugin: PaperPlugin
}