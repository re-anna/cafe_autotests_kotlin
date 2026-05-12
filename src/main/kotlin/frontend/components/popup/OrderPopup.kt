package frontend.components.popup

import com.codeborne.selenide.Selectors.byTestId
import com.codeborne.selenide.Selenide.element
import frontend.helpers.extractInt
import frontend.helpers.extractStatus
import io.qameta.allure.Step

class OrderPopup {

    private val orderId get() = element(byTestId("order-popup-id"))
    private val orderStatus get() = element(byTestId("order-popup-status"))
    private val orderCloseBtn get() = element(byTestId("order-popup-close"))

    @Step("Get order id")
    fun getOrderId(): Int = orderId.text().extractInt()

    @Step("Get order status")
    fun getOrderStatus(): String = orderStatus.text().extractStatus()

    @Step("Close order popup")
    fun close(): OrderPopup {
        orderCloseBtn.click()
        return this
    }


}

