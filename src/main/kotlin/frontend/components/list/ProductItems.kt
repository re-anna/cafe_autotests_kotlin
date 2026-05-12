package frontend.components.list

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.helpers.priceToCents
import frontend.models.ProductUi

data class ProductItem(
    val image: SelenideElement?,
    val name: String,
    val description: String,
    val priceCents: Int,
    val btnDecrement: SelenideElement?,
    var quantity: Int?,
    val btnIncrement: SelenideElement?
)

class ProductItems(private val items: ElementsCollection) {

    fun getProducts(): List<ProductItem> =
        items.map { card ->
            ProductItem(
                image = card.find(byDataTestGroup("product-card-image")),
                name = card.find(byDataTestGroup("product-card-name")).text,
                description = card.find(byDataTestGroup("product-card-description")).text,
                priceCents = card.find(byDataTestGroup("product-card-price")).text.priceToCents(),
                btnIncrement = card.find(byDataTestGroup("product-card-increment")),
                quantity = card.find(byDataTestGroup("product-card-qty")).text.toInt(),
                btnDecrement = card.find(byDataTestGroup("product-card-decrement"))
            )
        }

    fun productUiModel(): List<ProductUi> =
        items.map { card ->
            ProductUi(
                name = card.find(byDataTestGroup("product-card-name")).text,
                description = card.find(byDataTestGroup("product-card-description")).text,
                priceCents = card.find(byDataTestGroup("product-card-price")).text.priceToCents()
            )
        }
}



