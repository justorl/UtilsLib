package com.pulse.utilslib.nexoforge.auto

import com.pulse.nexoforge.api.NexoForgeItem
import com.pulse.utilslib.paper.plugin.PluginContext

abstract class AutoNexoItems {

    open val namespace = PluginContext.plugin.id
    abstract val fileName: String
    abstract val items: List<NexoForgeItem>

    fun register() {
        PluginContext.nexoForge.registerItems(items, "$namespace/$fileName.yml")
    }
}