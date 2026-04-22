package config

import java.util.Properties

object Config {

    data class Props(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val backendApiVersion: String,
        val moonHost: String,
        val runRemote: Boolean,
    )

    private const val DEFAULT_PROP_FILE = "/example.properties"
    
    val get: Props by lazy {
        val fileName = System.getProperty("env_config") ?: DEFAULT_PROP_FILE

        val properties = Properties().apply {
            val stream = Config::class.java.getResourceAsStream(fileName)
                ?: throw IllegalStateException("Properties file '$fileName' not found")
            stream.use { load(it) }
        }

        fun Properties.required(key: String): String =
            getProperty(key) ?: error("Необходимый конфиг '$key' не найден в '$fileName'")

        Props(
            browserName = properties.required("browser.name"),
            browserVersion = properties.required("browser.version"),
            frontendUrl = properties.required("frontend.url"),
            backendUrl = properties.required("backend.url"),
            backendApiVersion = properties.required("backend.api.version"),
            moonHost = properties.required("moon.host"),
            runRemote = properties.required("run.remote").toBoolean())
    }
}