package com.pulse.utilslib.folialib.extension

import com.pulse.utilslib.paper.plugin.PluginContext
import com.tcoded.folialib.wrapper.task.WrappedTask

fun runLaterFolia(delay: Long, task: () -> Unit): WrappedTask =
    PluginContext.foliaLib.scheduler.runLater(task, delay)

fun runLaterAsyncFolia(delay: Long, task: () -> Unit): WrappedTask =
    PluginContext.foliaLib.scheduler.runLaterAsync(task, delay)

fun runTimerFolia(delay: Long, delay2: Long, task: () -> Unit): WrappedTask =
    PluginContext.foliaLib.scheduler.runTimer(task, delay, delay2)

fun runTimerAsyncFolia(delay: Long, delay2: Long, task: () -> Unit): WrappedTask =
    PluginContext.foliaLib.scheduler.runTimerAsync(task, delay, delay2)