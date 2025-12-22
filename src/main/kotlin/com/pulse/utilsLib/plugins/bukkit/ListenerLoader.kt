package com.pulse.utilsLib.plugins.bukkit

import com.pulse.utilsLib.plugins.AbstractClassLoader
import com.pulse.utilsLib.plugins.bukkit.Instance.plugin
import org.bukkit.Bukkit

class ListenerLoader(
    plugin: PluginSetup
) : AbstractClassLoader<ListenerSetup>(plugin, ListenerSetup::class) {

    override fun handle(instance: ListenerSetup): Boolean {
        if (!instance.autoRegister) return false

        Bukkit.getPluginManager().registerEvents(instance, plugin)
        return true
    }

    override fun name() = "listeners"
}
