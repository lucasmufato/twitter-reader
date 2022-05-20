package com.sytac.excercise.infrastructure

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.yaml.YamlParser
import java.time.Duration


object ConfigurationProvider {

    private const val configurationFileName = "/config.yaml"

    data class Configuration constructor(
        val consumerKey: String,
        val consumerSecret: String,
        val trackMessagesContaining: String,
        val amountOfMessagesToTrack: Int,
        val amountOfTimeToTrack: Duration
    )

    val config: Configuration = ConfigLoader.Builder()
        .addFileExtensionMapping("yaml", YamlParser())
        .build()
        .loadConfigOrThrow(configurationFileName)
}
