package basic

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import frontend.helpers.BaseUiTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("basic")
class GoogleTest : BaseUiTest(){

    @Test
    @DisplayName("Open Google in selenoid and check title")
    fun openGoogle(){
        Selenide.open("https://www.google.com")
        WebDriverRunner.getWebDriver().title shouldBe "Google"
    }
}