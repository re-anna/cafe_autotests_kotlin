package backend

import backend.api.extension.ResponseExt.getAsObject
import backend.api.extension.ResponseExt.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.api.models.products.defaultProduct
import backend.api.models.users.randomUser
import backend.controllers.Controllers
import frontend.components.popup.CreateUserPopup
import frontend.components.popup.LogInPopup
import frontend.helpers.BaseTest
import frontend.pages.MainPage
import infra.junit.TestContext
import infra.junit.TestContext.token
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

@Tag("backend")
class OrderTest: BaseTest() {

    @Test
    @DisplayName("Check creation of valid order")
    fun createValidOrder(){
        val product = products.createProduct(product = defaultProduct()).getAsObject()

        val orderRequest = CreateOrderRequest(
            TestContext.user.id,
            products = listOf(CreateOrderRequest.Product(id = product.id))
        )
        val order = orders.createOrder(body = orderRequest)

        order.userId shouldBe TestContext.user.id
        order.products.first().id shouldBe product.id
    }

    @Test
    @DisplayName("Check creation of invalid order with empty products list")
    fun createInvalidOrderEmptyProducts() {
        val product = products.createProduct(product = defaultProduct()).getAsObject()

        val orderRequest = CreateOrderRequest(
            TestContext.user.id,
            products = emptyList()
        )

        val response = orders.createOrder(body = orderRequest)

        val error = response
    }

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
    }
}