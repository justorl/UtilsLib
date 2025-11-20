package com.pulse.utilsLib.plugin.bukkit

import com.pulse.utilsLib.plugin.Instance.foliaLib
import com.tcoded.folialib.wrapper.task.WrappedTask

fun runLater(delay: Long, task: () -> Unit) = foliaLib.scheduler.runLater(task, delay)
fun runLaterAsync(delay: Long, task: () -> Unit) = foliaLib.scheduler.runLaterAsync(task, delay)
fun runTimer(delay: Long, delay2: Long, task: () -> Unit): WrappedTask? = foliaLib.scheduler.runTimer(task, delay, delay2)
fun runTimerAsync(delay: Long, delay2: Long, task: () -> Unit): WrappedTask? = foliaLib.scheduler.runTimerAsync(task, delay, delay2)