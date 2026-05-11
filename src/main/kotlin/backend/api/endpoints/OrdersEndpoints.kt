package backend.api.endpoints

import backend.api.models.orders.CreateOrderResponse
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.api.models.products.CreateProductsRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrdersEndpoints {

    @GET("orders/")
    fun getOrders(
        @Header(Headers.AUTHORIZATION) token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Call<List<CreateOrderResponse>>

    @GET("orders/{id}")
    fun getOrderById(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int
    ): Call<CreateOrderResponse>

    @GET("orders/user/{id}")
    fun getOrderByUserId(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int
    ): Call<List<CreateOrderResponse>>

    @POST("orders/create")
    fun postOrderCreate(
        @Header(Headers.AUTHORIZATION) token: String?,
        @Body body: CreateProductsRequest
    ) : Call<CreateOrderResponse>

    @PUT("orders/{id}/status")
    fun updateOrder(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int,
        @Body body: UpdateOrderStatusRequest,
    ) : Call<CreateOrderResponse>

}