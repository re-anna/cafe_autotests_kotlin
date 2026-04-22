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
    val txtTitle: SelenideElement get() = element(byDataTestId("products-title"))
    val listCards: ElementsCollection get() = elements(byDataTestGroup("product-card"))

    fun productName(card: SelenideElement) = card.find(byDataTestGroup("product-card-name"))
    fun productDescription(card: SelenideElement) = card.find(byDataTestGroup("product-card-description"))
    fun productPrice(card: SelenideElement) = card.find((byDataTestGroup("product-card-price")))

    @Step("Open Products Page")
    fun open(): ProductsPage {
        Selenide.open("/products")
        return this
    }

    @Step("Get products page title")
    fun getTitle(): String = txtTitle.text.trim()

    @Step("Get products list from page")
    fun getProductsNames(): List<String> {
        val names = elements(byDataTestGroup("product-card-name"))
        names.shouldHave(sizeGreaterThan(0))
        return names.map {it.text.trim()}
    }

    @Step ("Get products information")
    fun getProductsInfo(): List<ProductData> {
        listCards.shouldHave(sizeGreaterThan(0))
        return listCards.map { card ->
            ProductData(
                name = productName(card).text.trim(),
                description = productDescription(card).text.trim(),
                price = productPrice(card).text.trim()
            )
        }
    }
}