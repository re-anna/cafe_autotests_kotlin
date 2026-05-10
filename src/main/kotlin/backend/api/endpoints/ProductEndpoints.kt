package backend.api.endpoints

import backend.api.models.products.CreateProductsRequest
import backend.api.models.products.CreateProductsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsEndpoints {

    @GET("products")
    fun getProducts(): Call<List<CreateProductsResponse>>

    //поставили не int а any чтобы можно было поэксперементировать со стрингой и т д
    @GET("products/{id}")
    fun getProductById(
        @Path("id") id: Any
    ): Call<CreateProductsResponse>

    @POST("products/create")
    fun postCreateProduct(
        @Header(Headers.AUTHORIZATION) token: String?,
        @Body request: CreateProductsRequest
    ): Call<CreateProductsResponse>

    @DELETE("products/{id}")
    fun deleteProductById(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Any
    ): Call<ResponseBody>

}