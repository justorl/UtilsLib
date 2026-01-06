package com.pulse.utilsLib.paper.extensions.folialib

import com.pulse.utilsLib.paper.plugin.PluginContext.foliaLib
import com.tcoded.folialib.wrapper.task.WrappedTask

fun runLater(delay: Long, task: () -> Unit): WrappedTask =
    foliaLib.scheduler.runLater(task, delay)

fun runLaterAsync(delay: Long, task: () -> Unit): WrappedTask =
    foliaLib.scheduler.runLaterAsync(task, delay)

fun runTimer(delay: Long, delay2: Long, task: () -> Unit): WrappedTask =
    foliaLib.scheduler.runTimer(task, delay, delay2)

fun runTimerAsync(delay: Long, delay2: Long, task: () -> Unit): WrappedTask =
    foliaLib.scheduler.runTimerAsync(task, delay, delay2)