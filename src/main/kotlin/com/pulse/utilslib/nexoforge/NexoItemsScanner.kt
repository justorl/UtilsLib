package com.pulse.utilslib.nexoforge

import com.pulse.utilslib.nexoforge.auto.AutoNexoItems
import com.pulse.utilslib.paper.PaperClassScanner
import com.pulse.utilslib.paper.plugin.PaperPlugin

class NexoItemsScanner(
    plugin: PaperPlugin
) : PaperClassScanner<AutoNexoItems>(plugin, AutoNexoItems::class) {

    override val name = "nexo forge items"

    override fun handle(instance: AutoNexoItems): Boolean {
        instance.register()
        return true
    }
}