package com.pulse.utilslib.paper.extension

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

fun reloadYaml(plugin: Plugin, file: File, name: String): YamlConfiguration {
    if (!file.exists()) {
        plugin.saveResource(name, false)
    }

    val yaml = YamlConfiguration.loadConfiguration(file)

    val defaultYaml = YamlConfiguration.loadConfiguration(
        plugin.getResource(name)?.reader() ?: return yaml
    )

    defaultYaml.getKeys(true)
        .filterNot { yaml.contains(it) }
        .forEach { key -> yaml.set(key, defaultYaml.get(key)) }

    yaml.save(file)
    return yaml
}