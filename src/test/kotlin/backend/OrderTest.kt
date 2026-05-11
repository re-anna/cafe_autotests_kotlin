package backend

import backend.api.extension.ResponseExt.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.products.defaultProduct
import backend.controllers.Controllers
import frontend.helpers.BaseTest
import infra.junit.TestContext
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

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
    fun createInvalidOrderEmptyProducts(){
        val product = products.createProduct(product = defaultProduct()).getAsObject()

        val orderRequest = CreateOrderRequest(
            TestContext.user.id,
            products = emptyList()
        )

        val order = orders.createOrder(body = orderRequest)

    }

}