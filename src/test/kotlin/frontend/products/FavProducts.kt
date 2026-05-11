package frontend.products

import backend.api.models.products.CreateProductsRequest
import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Feature("")
@Story("Products")
@Tag("e2e")
class FavProducts: BaseUiTest() {

    private val controllers = Controllers()

    @Test
    @DisplayName("Favorite products check")
    fun testFavoriteProductsExists(){
        val product = controllers.products.createProduct(
            product = CreateProductsRequest(
            name = "Orange coffee",
            description = "Coffee with orange syrup",
            price = 4.0
        )
        ).getAsObject()

        val popularList = MainPage()
            .open()
            .getPopularProducts()

        popularList.size shouldBeGreaterThanOrEqual 1
        popularList shouldContain product
    }
}