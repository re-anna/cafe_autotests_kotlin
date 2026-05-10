package config

import java.util.Properties

object Config {

    data class Props(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val selenoidUrl: String,
        val dbUrl: String,
        val dbUsername: String,
        val dbPassword: String
    )

    private const val DEFAULT_PROP_FILE = "/example.properties"
    
    val get: Props by lazy {
        val fileName = System.getProperty("env_config",DEFAULT_PROP_FILE)

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
            selenoidUrl = properties.required("selenoidUrl"),
            dbUrl = properties.required("dbUrl"),
            dbUsername = properties.required("dbUsername"),
            dbPassword = properties.required("dbPassword")

        )
    }
}