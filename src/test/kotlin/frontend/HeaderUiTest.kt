package frontend

import com.codeborne.selenide.WebDriverRunner.url
import frontend.helpers.BaseUiTest
import frontend.helpers.HeaderLinks
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

@Tag("frontend")
class HeaderUiTest: BaseUiTest() {

    @Test
    @DisplayName("Check Header titles")
    fun checkHeaderTitles(){
        val expected =listOf("Brew & Bean", "Products", "Orders", "Contact","Cart", "Join")
        val actual = MainPage().open().header().getLinksText()

        actual shouldBe expected
    }

    @ParameterizedTest
    @EnumSource(HeaderLinks::class)
    @DisplayName("Open every page from header and check url")
    fun checkAllHeaderLinks(link: HeaderLinks){
        MainPage()
            .open()
            .header()
            .clickLinkAndWaitUrl(link.urlName, link.expectedUrl)

        url() shouldContain link.expectedUrl
    }
}