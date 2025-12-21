package com.pulse.utilsLib.plugin

import com.pulse.utilsLib.plugin.bukkit.hasClassGraph
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfoList
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class ListenerLoader(
    private val plugin: Plugin
) {
    fun load() {
        if (!hasClassGraph()) {
            if (Instance.plugin.verboseOutput) println("UtilsLib: No classgraph available")
            return
        }

        ClassGraph()
            .enableAllInfo()
            .acceptPackages(plugin::class.java.`package`.name)
            .enableExternalClasses()
            .scan().use { scanResult ->
                val info: ClassInfoList = scanResult.getSubclasses(ListenerSetup::class.java)

                if (Instance.plugin.verboseOutput) println("UtilsLib: Found ${info.size} listeners")
                info.forEach { classInfo ->
                    try {
                        val listener = classInfo.loadClass(ListenerSetup::class.java).getDeclaredConstructor().newInstance()
                        if (listener.autoRegister) {
                            Bukkit.getPluginManager().registerEvents(listener, plugin)
                            if (Instance.plugin.verboseOutput) println("UtilsLib: Registered listener: ${listener::class.simpleName}")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        println("UtilsLib: Could not register listener: ${classInfo.simpleName}")
                        return@forEach
                    }
                }
        }
    }
}