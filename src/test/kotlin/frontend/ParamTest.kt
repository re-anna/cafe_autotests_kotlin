package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.ProductData
import frontend.pages.ProductsPage
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAllInAnyOrder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@Tag("frontend")
class ParamTest : BaseUiTest() {

    @ParameterizedTest
    @DisplayName("Проверить названия продуктов")
    @ValueSource(strings = ["Coca Cola", "Coffee", "Tea", "Bubble Tea", "Water", "Juice"])
    fun checkProducts(links: String){
        val productsLinks = ProductsPage().getProductsInfo()
        productsLinks.shouldContainAllInAnyOrder(links)
    }

    @ParameterizedTest
    @CsvSource(
        """Coca Cola, A wonderful coca cola for your daily brew., $2.33""",
        """Coffee, A wonderful coffee for your daily brew., $4.49""",
        """Tea, A wonderful tea for your daily brew., $3.25""",
        """Bubble Tea,A wonderful bubble tea for your daily brew.,$2""",
        """Water, A wonderful water for your daily brew., $1""",
        """Juice, A wonderful juice for your daily brew., $3.75"""
    )
    @DisplayName("Проверить текст всех продуктов")
    fun checkAllProductsText(product: String, description: String, price: Int) {
        val products = ProductsPage().getProductsInfo()
        products shouldContain ProductData(product, description, price)
    }
}