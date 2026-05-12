package frontend.pages

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.components.list.ProductItems
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.helpers.Wrappers.byDataTestId
import frontend.helpers.priceToCents
import frontend.models.ProductUi
import io.kotest.matchers.collections.shouldHaveSize
import io.qameta.allure.Step
import kotlin.String

data class OrderData(
    val orderId: Int,
    val status: String,
    val productName: String,
    val qty: String,
    val unitPrice: Int,
    val orderProductPrice: Int,
    val totalPrice: Int
)

class OrdersPage  {
    private val orderCards: ElementsCollection get() = elements(byDataTestGroup("order-card"))

    fun orderId(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-id"))
    fun status(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-status"))
    fun productsInOrder(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-product-name"))
    fun productQty(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-product-qty"))
    fun unitPrice(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-product-unit-price"))
    fun productPrice(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-product-price"))
    fun totalPrice(orderCards: SelenideElement) = orderCards.find(byDataTestGroup("order-total"))


    @Step("Open Orders Page")
    fun open(): OrdersPage {
        Selenide.open("/orders")
        return this
    }

    @Step("Get orders info")
    fun getOrdersInfo(): List<OrderData>  {
        orderCards.shouldHave(sizeGreaterThan(0))

        return orderCards.map { order ->
            OrderData(
                orderId = orderId(order).text.toInt(),
                status = status(order).text(),
                productName = productsInOrder(order).text(),
                qty = productQty(order).text(),
                unitPrice = unitPrice(order).text.priceToCents(),
                orderProductPrice = productPrice(order).text.priceToCents(),
                totalPrice = totalPrice(order).text.priceToCents()
            )
        }
    }



}