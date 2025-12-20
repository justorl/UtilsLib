package com.pulse.utilsLib.plugin

import org.bukkit.Bukkit
import org.bukkit.event.Listener

abstract class ListenerSetup(
    autoRegister: Boolean = true,
) : Listener {
    companion object {
        val listeners = mutableListOf<ListenerSetup>()
    }

    init {
        if (autoRegister) listeners.add(this)
    }

    fun register() =
        Bukkit.getPluginManager().registerEvents(this, Instance.plugin)
}