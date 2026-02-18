package com.pulse.utilslib.paper.extension

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager
import org.bukkit.plugin.Plugin

fun Plugin.lifecycle(
    eventManager: LifecycleEventManager<Plugin>.() -> Unit,
) = lifecycleManager.also { with(lifecycleManager, eventManager) }
