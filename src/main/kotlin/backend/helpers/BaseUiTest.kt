package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import config.Config
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest: BaseTest() {

    init {
        Configuration.baseUrl = Config.get.frontendUrl
        Configuration.timeout = 15_000
        Configuration.pageLoadStrategy = "normal"
        Configuration.reopenBrowserOnFail = true
      //  Configuration.browser = DriverProvider::class.java.name
        }

    @BeforeEach
    fun openBrowser() {
        Selenide.open("/")
    }


    @AfterEach
    fun cleanBrowser(){
        Selenide.clearBrowserCookies()
        Selenide.clearBrowserLocalStorage()
    }
}