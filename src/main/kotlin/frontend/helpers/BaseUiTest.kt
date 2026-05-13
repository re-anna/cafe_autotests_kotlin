package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import config.Config
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest: BaseTest() {

    init {
        Configuration.baseUrl = Config.get.frontendUrl
        Configuration.timeout = 15_000
        Configuration.pageLoadStrategy = "normal"
        Configuration.reopenBrowserOnFail = true

        val remote = System.getProperty("remote", "false").toBoolean()
        if (remote) {
            Configuration.browser = DriverProvider::class.java.name
        } else {
            Configuration.browser = "chrome"
        }
    }

        @BeforeEach
        fun openBrowser() {
            Selenide.open("/")
        }


        @AfterEach
        fun cleanBrowser() {
            if (WebDriverRunner.hasWebDriverStarted()) {
                Selenide.clearBrowserCookies()
                Selenide.clearBrowserLocalStorage()
                Selenide.closeWebDriver()
            }
        }
}