package com.pulse.utilsLib.paper

import com.pulse.utilsLib.paper.extensions.hasClassGraph
import com.pulse.utilsLib.paper.plugin.PaperPlugin
import com.pulse.utilsLib.paper.plugin.PluginContext
import io.github.classgraph.ClassGraph
import kotlin.reflect.KClass

abstract class PaperClassScanner<T: Any>(
    private val plugin: PaperPlugin,
    private val baseClass: KClass<T>
) {
    abstract val name: String

    protected val verbose = PluginContext.plugin.verboseOutput
    protected val id = plugin.id

    protected abstract fun handle(instance: T): Boolean

    fun load(pkg: String? = null) {
        val scanPkg = pkg ?: plugin::class.java.`package`.name

        if (!hasClassGraph()) {
            log("No classgraph available")
            return
        }

        log("Registering $name in $scanPkg..")
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
                        log("Could not register $name: ${classInfo.simpleName}")
                    }
                }
            }

        log("Done registering $name! Registered $count")
    }

    protected open fun log(msg: String) {
        if (verbose) println("[$id - UtilsLib] $msg")
    }
}