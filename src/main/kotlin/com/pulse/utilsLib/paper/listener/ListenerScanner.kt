package com.pulse.utilslib.paper.listener

import com.pulse.utilslib.paper.PaperClassScanner
import com.pulse.utilslib.paper.listener.auto.AutoListener
import com.pulse.utilslib.paper.plugin.PaperPlugin
import com.pulse.utilslib.paper.plugin.PluginContext.plugin
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
