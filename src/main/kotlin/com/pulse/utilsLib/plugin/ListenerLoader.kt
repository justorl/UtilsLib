package com.pulse.utilsLib.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.reflections.Reflections

class ListenerLoader(
    private val plugin: Plugin
) {
    fun load() {
        val pkg = plugin::class.java.`package`.name
        val reflections = Reflections(pkg)
        val classes = reflections.getSubTypesOf(ListenerSetup::class.java)

        classes.forEach { clazz ->
            val ctor = clazz.getDeclaredConstructor()
            val listener = ctor.newInstance()
            Bukkit.getPluginManager().registerEvents(listener, plugin)
        }
    }
}