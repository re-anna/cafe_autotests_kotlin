package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NavigationTest: BaseUiTest() {

    @Test
    @DisplayName("Open Product page from header")
    fun openProductsFromHeader(){
        val productsPage = MainPage().open().header().goToProducts()
        productsPage.getTitle() shouldBe "All Products"
    }
}