package com.pulse.utilsLib.paper.command

import com.pulse.utilsLib.paper.PaperClassScanner
import com.pulse.utilsLib.paper.plugin.PaperPlugin

class CommandScanner(
    plugin: PaperPlugin
) : PaperClassScanner<AutoCommand>(plugin, AutoCommand::class) {

    override val name = "commands"

    override fun handle(instance: AutoCommand): Boolean {
        instance.register()
        return true
    }
}
