package frontend.pages

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.components.list.ProductItem
import frontend.components.list.ProductItems
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.helpers.Wrappers.byDataTestId
import frontend.helpers.priceToCents
import io.qameta.allure.Step

data class ProductData(
    val name: String,
    val description: String,
    val price: Int
)

class ProductsPage {
    private val title: SelenideElement get() = element(byDataTestId("products-title"))
    private val cards: ElementsCollection get() = elements(byDataTestGroup("product-card"))

    fun getTitle(): String = title.text

    fun productName(card: SelenideElement) = card.find(byDataTestGroup("product-card-name"))
    fun productDescription(card: SelenideElement) = card.find(byDataTestGroup("product-card-description"))
    fun productPrice(card: SelenideElement) = card.find((byDataTestGroup("product-card-price")))

    @Step("Open Products Page")
    fun open(): ProductsPage {
        Selenide.open("/products")
        return this
    }

    @Step("Get popular products")
    fun getPopularProducts(): ElementsCollection = cards

    @Step ("Get products information")
    fun getProductsInfo(): List<ProductData> {
        cards.shouldHave(sizeGreaterThan(0))
        return cards.map { card ->
            ProductData(
                name = productName(card).text(),
                description = productDescription(card).text(),
                price = productPrice(card).text.priceToCents()
            )
        }
    }
    @Step("Get list of all products from page as objects")
    fun getProductsAsObjects(): List<ProductItem> =
        ProductItems(cards).getProducts()

    @Step("Count products with {word}")
    fun countProductsWithWord(word: String): Int =
        getProductsAsObjects().count{ it.name.contains(word, ignoreCase = true) }
}