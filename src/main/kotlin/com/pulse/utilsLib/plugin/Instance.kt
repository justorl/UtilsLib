package com.pulse.utilsLib.plugin

import com.tcoded.folialib.FoliaLib
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary

object Instance {
    lateinit var foliaLib: FoliaLib
    lateinit var scoreboardLib: ScoreboardLibrary
    lateinit var plugin: PluginSetup
}