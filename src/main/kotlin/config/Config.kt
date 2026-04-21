package org.example.config

import java.util.Properties

object Config {
    private const val DEFAULT_PROP_FILE = "/example.properties"

    data class Props(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val backendApiVersion: String,
        val moonHost: String,
    )

    val props: Props by lazy {
        val fileName = System.getProperty("env_config") ?: DEFAULT_PROP_FILE

        val properties = Properties().apply {
            val stream = Config::class.java.getResourceAsStream(fileName)
                ?: throw IllegalStateException("Properties file '$fileName' not found")
            stream.use { load(it) }
        }

        fun Properties.required(key: String): String =
            getProperty(key) ?: error("Необходимый конфиг '$key' не найден в '$fileName'")

        Props(
            browserName = properties.getProperty("browser.name"),
            browserVersion = properties.getProperty("browser.version"),
            frontendUrl = properties.getProperty("frontend.url"),
            backendUrl = properties.getProperty("backend.url"),
            backendApiVersion = properties.getProperty("backend.api.version"),
            moonHost = properties.getProperty("moon.host")
        )
    }
}