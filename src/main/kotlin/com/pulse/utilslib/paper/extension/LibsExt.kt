package com.pulse.utilslib.paper.extension

import com.pulse.utilslib.core.extension.classExists

fun hasFoliaLib(): Boolean =
    classExists("com.tcoded.folialib.FoliaLib")

fun hasCommandApi(): Boolean =
    classExists("dev.jorel.commandapi.CommandAPI")

fun hasScoreboardLib(): Boolean =
    classExists("net.megavex.scoreboardlibrary.api.ScoreboardLibrary")

fun hasLuckPermsApi(): Boolean =
    classExists("net.luckperms.api.LuckPermsProvider")

fun hasClassGraph(): Boolean =
    classExists("io.github.classgraph.ClassGraph")

fun hasNexoForge(): Boolean =
    classExists("com.pulse.nexoforge.NexoForge")

fun isFolia() : Boolean =
    classExists("io.papermc.paper.threadedregions.scheduler.RegionScheduler")