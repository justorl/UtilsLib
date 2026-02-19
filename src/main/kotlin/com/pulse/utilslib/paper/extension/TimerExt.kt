package com.pulse.utilslib.paper.extension

import com.pulse.utilslib.paper.plugin.PluginContext
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

fun runLater(delay: Long, task: () -> Unit): BukkitTask =
    Bukkit.getScheduler().runTaskLater(PluginContext.plugin, task, delay)

fun runLaterAsync(delay: Long, task: () -> Unit): BukkitTask =
    Bukkit.getScheduler().runTaskLaterAsynchronously(PluginContext.plugin, task, delay)

fun runTimer(delay: Long, delay2: Long, task: () -> Unit): BukkitTask =
    Bukkit.getScheduler().runTaskTimer(PluginContext.plugin, task, delay, delay2)

fun runTimerAsync(delay: Long, delay2: Long, task: () -> Unit): BukkitTask =
    Bukkit.getScheduler().runTaskTimerAsynchronously(PluginContext.plugin, task, delay, delay2)