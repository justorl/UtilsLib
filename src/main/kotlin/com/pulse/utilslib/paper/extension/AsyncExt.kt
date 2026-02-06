package com.pulse.utilslib.paper.extension

import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.Bukkit

class AsyncChain<T>(
    private val asyncBlock: () -> T
) {
    infix fun thenSync(syncBlock: (T) -> Unit) {
        Bukkit.getScheduler().runTaskAsynchronously(PluginContext.plugin, Runnable {
            val result = asyncBlock()
            Bukkit.getScheduler().runTask(PluginContext.plugin, Runnable {
                syncBlock(result)
            })
        })
    }
}

fun <T> async(block: () -> T): AsyncChain<T> = AsyncChain(block)