package ui

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FirstUiTest : BaseUiTest() {

    @Test
    @DisplayName("Check name of CoffeePlace on main page")
    fun testFirstUI() {
        val title = MainPage()
            .getTitle()

        title shouldBe "Welcome to Brew & Bean"
    }

    @Test
    @DisplayName("Check MainPage Header UI")
    fun checkHeaderTitles() {
        MainPage().getTitle()
    }
}