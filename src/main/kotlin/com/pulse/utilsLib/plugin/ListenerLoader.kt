package com.pulse.utilsLib.plugin

import com.pulse.utilsLib.plugin.bukkit.hasClassGraph
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfoList
import org.bukkit.Bukkit

class ListenerLoader(
    private val plugin: PluginSetup
) {
    fun load(pkg: String? = null) {
        val verbose = Instance.plugin.verboseOutput;
        val id = plugin.ID
        val pkg = pkg ?: plugin::class.java.`package`.name

        if (!hasClassGraph()) {
            if (verbose) println("[$id - UtilsLib] No classgraph available")
            return
        }

        var listenersCount = 0

        ClassGraph()
            .enableAllInfo()
            .acceptPackages(pkg)
            .enableExternalClasses()
            .scan().use { scanResult ->
                val info: ClassInfoList = scanResult.getSubclasses(ListenerSetup::class.java)

                info.forEach { classInfo ->
                    try {
                        val listener = classInfo.loadClass(ListenerSetup::class.java).getDeclaredConstructor().newInstance()
                        if (listener.autoRegister) {
                            Bukkit.getPluginManager().registerEvents(listener, plugin)
                            if (verbose) println("[$id - UtilsLib] Registered listener: ${listener::class.simpleName}")
                            listenersCount++
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        println("[$id - UtilsLib] Could not register listener: ${classInfo.simpleName}")
                        return@forEach
                    }
                }
        }
        if (verbose) println("[$id - UtilsLib] Done registering in $pkg! Registered $listenersCount listeners")
    }
}