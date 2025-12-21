package com.pulse.utilsLib.plugin

import org.bukkit.Bukkit
import org.bukkit.event.Listener

abstract class ListenerSetup(
    val autoRegister: Boolean = true,
) : Listener {
    fun register() =
        Bukkit.getPluginManager().registerEvents(this, Instance.plugin)
}