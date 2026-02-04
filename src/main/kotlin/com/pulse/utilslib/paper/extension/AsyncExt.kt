package com.pulse.utilslib.paper.extension

import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.Bukkit

class AsyncChain(
    private val asyncBlock: () -> Unit
) {
    infix fun thenSync(syncBlock: () -> Unit) {
        Bukkit.getScheduler().runTaskAsynchronously(PluginContext.plugin, Runnable {
            asyncBlock()
            Bukkit.getScheduler().runTask(PluginContext.plugin, syncBlock)
        })
    }
}

fun async(block: () -> Unit): AsyncChain =
    AsyncChain(block)