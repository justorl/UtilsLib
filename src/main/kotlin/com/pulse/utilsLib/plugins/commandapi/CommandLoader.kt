package com.pulse.utilsLib.plugins.commandapi

import com.pulse.utilsLib.plugins.AbstractClassLoader
import com.pulse.utilsLib.plugins.bukkit.PluginSetup

class CommandLoader(
    plugin: PluginSetup
) : AbstractClassLoader<CommandSetup>(plugin, CommandSetup::class) {

    override fun handle(instance: CommandSetup): Boolean {
        instance.register()
        return true
    }

    override fun name() = "commands"
}
