package com.pulse.utilsLib.plugins

import com.pulse.utilsLib.plugins.bukkit.Instance
import com.pulse.utilsLib.plugins.bukkit.PluginSetup
import com.pulse.utilsLib.plugins.bukkit.utils.hasClassGraph
import io.github.classgraph.ClassGraph
import kotlin.reflect.KClass

abstract class AbstractClassLoader<T : Any>(
    private val plugin: PluginSetup,
    private val baseClass: KClass<T>
) {

    protected val verbose = Instance.plugin.verboseOutput
    protected val id = plugin.ID

    fun load(pkg: String? = null) {
        val scanPkg = pkg ?: plugin::class.java.`package`.name

        if (!hasClassGraph()) {
            log("No classgraph available")
            return
        }

        log("Registering ${name()} in $scanPkg..")
        var count = 0

        ClassGraph()
            .enableAllInfo()
            .acceptPackages(scanPkg)
            .enableExternalClasses()
            .scan().use { scanResult ->
                val classes = scanResult.getSubclasses(baseClass.java)

                classes.forEach { classInfo ->
                    try {
                        val instance = classInfo
                            .loadClass(baseClass.java)
                            .getDeclaredConstructor()
                            .newInstance()

                        if (handle(instance)) {
                            count++
                            log("Registered ${instance::class.simpleName}..")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        log("Could not register ${name()}: ${classInfo.simpleName}")
                    }
                }
            }

        log("Done registering ${name()}! Registered $count")
    }

    protected abstract fun handle(instance: T): Boolean
    protected abstract fun name(): String

    protected fun log(msg: String) {
        if (verbose) println("[$id - UtilsLib] $msg")
    }
}
