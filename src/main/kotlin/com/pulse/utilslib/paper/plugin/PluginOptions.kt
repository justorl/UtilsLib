package com.pulse.utilslib.paper.plugin

import com.tcoded.folialib.util.FoliaLibOptions
import java.io.File

data class CommandAPIOptions(
    val id: String? = null,
    val verboseOutput: Boolean? = null,
    val fallbackToLatestNMS: Boolean? = null,
    val addSkipSenderProxy: List<String>? = null,
    val silentLogs: Boolean? = null,
    val missingExecutorImplementationMessage: String? = null,
    val dispatcherFile: File? = null,
    val enableNetworking: Boolean? = null,
    val makeNetworkingExceptionsWarnings: Boolean? = null,
)

data class PluginOptions(
    val verboseOutput: Boolean = false,
    val foliaLibOptions: FoliaLibOptions = FoliaLibOptions(),
    val commandApiOptions: CommandAPIOptions = CommandAPIOptions(verboseOutput = verboseOutput),
)