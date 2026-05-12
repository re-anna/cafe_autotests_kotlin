package frontend.e2e

import OrderStatus
import backend.api.extension.ResponseExt.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.api.models.products.defaultProduct
import frontend.components.popup.OrderPopup
import frontend.helpers.AuthHelperUI
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import infra.junit.TestContext
import infra.junit.TestContext.token
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

@Tag("e2e")
class OrdersE2ETest : BaseUiTest() {

    @ParameterizedTest
    @EnumSource(OrderStatus::class)
    @DisplayName("Parametrized order status check")
    fun changeOrderStatus(status: OrderStatus){
        val product = products.createProduct(
            token = token,
            product = defaultProduct()
        ).getAsObject()

        val order = orders.createOrder(
            token = token,
            body = CreateOrderRequest(
                TestContext.user.id,
                listOf(CreateOrderRequest.Product(id = product.id))
            )
        )

        val update = orders.updateOrderById(token,order.id, UpdateOrderStatusRequest(status.value))
        update.orderStatus shouldBe status.value

        AuthHelperUI.loginAsCurrentTestUser()

        val uiOrder = OrdersPage().open().findOrder(order.id)
        uiOrder.status shouldBe status.value
    }

    @Test
    @DisplayName("Create order via UI and verify via backend")
    fun createOrderUiBack(){

        AuthHelperUI.loginAsCurrentTestUser()

        val addFirstProduct = MainPage()
            .open()
            .getPopularProducts()
            .first()
        addFirstProduct.btnIncrement!!.click()


        MainPage()
            .open()
            .header()
            .clickLink("Cart")
            .openCartPopup()
            .createOrder()

        val orderIdUi = OrderPopup().getOrderId()
        val orderStatusUi = OrderPopup().getOrderStatus()

        val ordersAfter = orders.getOrderById(
            token,
            orderIdUi
        )

        ordersAfter.id shouldBe orderIdUi
        ordersAfter.orderStatus shouldBe orderStatusUi
    }


}