package ui

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HeaderUiTest : BaseUiTest() {

    @Test
    @DisplayName("Check Header titles")
    fun checkHeaderTitles(){
        val expected = listOf("Brew & Bean", "Products", "Orders", "Contact","Cart", "Join")
        val actual = MainPage().open().header().getLinksText()
        actual shouldBe expected
    }
}