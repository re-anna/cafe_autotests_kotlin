package frontend.e2e

import backend.helpers.ProductsHelper
import frontend.helpers.BaseUiTest
import frontend.pages.ProductsPage
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("e2e")
class ProductsTest: BaseUiTest() {

    val productsHelper = ProductsHelper()

    @Test
    @DisplayName("Check that created 5 products via backend is displayed on frontend")
    fun checkFiveProducts(){
        val listOfProducts = productsHelper.createProducts(5).sortedByDescending { it.name }

        val products = ProductsPage()
            .open()
            .getProductsAsObjects()

        products.forEachIndexed { index, item ->
            item.name shouldBe listOfProducts[index].name
        }
    }

    @Test
    @DisplayName("Create 'Chockolate' products via back and then check them on frontend and backend")
    fun checkCreatedItems(){
        val word = "Chockolate"
        productsHelper.ensureProductsWithWordExists(word = word, minCount = 5)

        val frontendCoffeeCount = ProductsPage()
            .open()
            .countProductsWithWord(word)

        val backendProductCount = productsHelper.countProductsWithWordBackend(word)

        frontendCoffeeCount shouldBeEqual backendProductCount
    }
}
