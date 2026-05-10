package frontend.components.list

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.helpers.priceToCents

data class CartItem(
    val image: SelenideElement,
    val name: String,
    val price: Int,
    val btnDecrement: SelenideElement,
    val quantity: Int,
    val btnIncrement: SelenideElement,
    val btnDeleteAll: SelenideElement

)
class CartItems (val listCartProducts: ElementsCollection) {

    fun getItems(): List<CartItem>{
        return listCartProducts
            .map { CartItem(
                    image = it.find(byDataTestGroup("cart-item-image")),
                    name = it.find(byDataTestGroup("cart-item-name")).text,
                    price = it.find(byDataTestGroup("cart-item-price")).text.priceToCents(),
                    btnIncrement = it.find(byDataTestGroup("cart-item-increment")),
                    btnDecrement = it.find(byDataTestGroup("cart-item-decrement")),
                    quantity = it.find(byDataTestGroup("cart-item-qty")).text.toInt(),
                    btnDeleteAll = it.find(byDataTestGroup("cart-item-remove"))
                )
            }
    }
}