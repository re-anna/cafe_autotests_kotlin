package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.orders.CreateOrderResponse
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.api.extension.ResponseExt.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.helpers.AuthHelper
import backend.helpers.GarbageCollector
import infra.junit.TestContext
import io.qameta.allure.Step
import retrofit2.Response

class OrderController: Endpoints() {

    private val authHelper = AuthHelper()

    @Step("Create order")
    fun createOrder(
        token: String = TestContext.token,
        body: CreateOrderRequest
    ): CreateOrderResponse {
        return orders.postOrderCreate(token,body)
            .execute()
            .getAsObject()
            .also { GarbageCollector.orders.add(it.id) }
    }

    @Step("Create order")
    fun createOrderRaw(
        token: String = TestContext.token,
        body: CreateOrderRequest
    ): Response<CreateOrderResponse> =
        orders.postOrderCreate(token,body).execute()


    @Step("Get all orders")
    fun getAllOrders(
        token: String = TestContext.token,
        offset: Int = 0,
        limit: Int = 100
    ): List<CreateOrderResponse> {
        return orders.getOrders(token, offset, limit).execute().getAsObject()
    }

    @Step("Get order by id")
    fun getOrderById(
        token: String = TestContext.token,
        id: Int
    ): CreateOrderResponse{
        return orders.getOrderById(token, id).execute().getAsObject()
    }

    @Step("Get all orders by user id")
    fun getOrderByUserId(
        token: String,
        id: Int
    ): List<CreateOrderResponse> {
        return orders.getOrderByUserId(token, id).execute().getAsObject()
    }

    @Step("Change order status")
    fun updateOrderById(
        token: String = TestContext.token,
        id: Int,
        body: UpdateOrderStatusRequest
    ): CreateOrderResponse {
        return orders.updateOrder(token, id, body).execute().getAsObject()
    }
}