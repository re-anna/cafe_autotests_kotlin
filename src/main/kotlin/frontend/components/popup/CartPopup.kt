package frontend.components.popup

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.list.CartItem
import frontend.components.list.CartItems
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.helpers.Wrappers.byDataTestId
import io.qameta.allure.Step

class CartPopup {
    private val txtTotalPrice get() = element(byDataTestId("cart-total-price"))
    private val btnCheckout get() = element(byDataTestId("cart-checkout"))
    private val newCartItems get() = elements(byDataTestGroup("cart-item"))

    @Step("Get all cart items")
    fun getCartProducts(): List<CartItem> {
        return CartItems(newCartItems).getItems()
    }

    @Step("Create order")
    fun createOrder(): CartPopup{
        btnCheckout.click()
        return this
    }
}