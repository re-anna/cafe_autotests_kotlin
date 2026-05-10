package backend.controllers

import backend.api.endpoints.Endpoints
import backend.helpers.AuthHelper
import backend.helpers.GarbageCollector
import backend.api.models.products.CreateProductsRequest
import backend.api.models.products.CreateProductsResponse
import backend.extension.ResponseExt.getAsObject
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class ProductController: Endpoints() {

    private val authHelper = AuthHelper()

    @Step("Get all products")
    fun getAllProducts(): Response<List<CreateProductsResponse>> {
        return products.getProducts().execute()
    }

    @Step("Get product by id")
    fun getProductById(id: Int): Response<CreateProductsResponse> {
        return products.getProductById(id).execute()
    }

    @Step("Create new product")
    fun createProduct(token: String? = authHelper.getAdminToken(), product: CreateProductsRequest): Response<CreateProductsResponse>{
        val response = products.postCreateProduct(token, product).execute()
            if (response.isSuccessful){
                GarbageCollector.products.add(response.getAsObject().id)
            }
        return response
    }

    @Step("Delete product with id: {id}")
    fun deleteProductById(token: String = authHelper.getAdminToken(), id: Any): Response<ResponseBody> {
        return products.deleteProductById(token, id).execute()
    }
}