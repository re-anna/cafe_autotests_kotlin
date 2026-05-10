package frontend.helpers

import com.codeborne.selenide.WebDriverProvider
import config.Config
import org.openqa.selenium.Capabilities
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI


class DriverProvider : WebDriverProvider {

    override fun createDriver(capabilities: Capabilities): RemoteWebDriver {
        val options = ChromeOptions().apply {
            setCapability("browserVersion", Config.get.browserVersion)
            setCapability(
                "selenoid:options",
                mapOf(
                    "headless" to false,
                    "enableVNC" to true,
                    "acceptsAllCertificates" to true,
                    "screenResolution" to "1920x1080"
                )
            )
            addArguments("--disable-gpu")
            addArguments("window-size=1920x1080")
        }
        return RemoteWebDriver(URI(Config.get.selenoidUrl).toURL(), options)
            .apply { fileDetector = LocalFileDetector() }
    }
}
