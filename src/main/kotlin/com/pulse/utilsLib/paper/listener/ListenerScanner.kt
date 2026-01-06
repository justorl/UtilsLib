package com.pulse.utilsLib.paper.listener

import com.pulse.utilsLib.paper.PaperClassScanner
import com.pulse.utilsLib.paper.plugin.PaperPlugin
import com.pulse.utilsLib.paper.plugin.PluginContext.plugin
import org.bukkit.Bukkit

class ListenerScanner(
    plugin: PaperPlugin
) : PaperClassScanner<AutoListener>(plugin, AutoListener::class) {

    override val name = "listeners"

    override fun handle(instance: AutoListener): Boolean {
        if (!instance.autoRegister) return false

        Bukkit.getPluginManager().registerEvents(instance, plugin)
        return true
    }
}
