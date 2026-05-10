package backend

import backend.api.models.ErrorResponse
import backend.api.models.invalidToken
import backend.api.models.products.CreateProductsRequest
import backend.helpers.AuthHelper
import backend.api.models.products.defaultProduct
import backend.extension.ResponseExt.getAsObject
import backend.extension.ResponseExt.getErrorAsObject
import frontend.helpers.BaseTest
import infra.junit.TestContext.token
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")
class ProductTests: BaseTest() {

    @Test
    @DisplayName("Create valid product")
    fun createValidProduct(){
        val baseProduct = products.createProduct(
            token,
            product = defaultProduct()
        ).getAsObject()

         val expectedProduct = products.getProductById(baseProduct.id).getAsObject()

        baseProduct shouldBe expectedProduct
    }

    @Test
    @DisplayName("Create product with null token")
    fun createProductWithNullToken(){
        val error = products.createProduct(token = null, product = defaultProduct()).getErrorAsObject<ErrorResponse>()

        error shouldBeEqual invalidToken
    }
}