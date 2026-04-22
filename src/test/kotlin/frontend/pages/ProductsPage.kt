package frontend.pages

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.helpers.Wrappers.byDataTestId
import io.qameta.allure.Step

data class ProductData(
    val name: String,
    val description: String,
    val price: String
)

class ProductsPage {
    val title: SelenideElement get() = element(byDataTestId("products-title"))
    val cards: ElementsCollection get() = elements(byDataTestGroup("product-card"))

    fun getTitle(): String = title.text.trim()

    fun productName(card: SelenideElement) = card.find(byDataTestGroup("product-card-name"))
    fun productDescription(card: SelenideElement) = card.find(byDataTestGroup("product-card-description"))
    fun productPrice(card: SelenideElement) = card.find((byDataTestGroup("product-card-price")))

    @Step("Open Products Page")
    fun open(): ProductsPage {
        Selenide.open("/products")
        return this
    }

    @Step ("Get products information")
    fun getProductsInfo(): List<ProductData> {
        cards.shouldHave(sizeGreaterThan(0))
        return cards.map { card ->
            ProductData(
                name = productName(card).text.trim(),
                description = productDescription(card).text.trim(),
                price = productPrice(card).text.trim()
            )
        }
    }
}