package backend

import backend.helpers.AuthHelper
import backend.api.models.products.defaultProduct
import backend.extension.ResponseExt.getAsObject
import frontend.helpers.BaseTest
import infra.junit.TestContext.token
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductTests: BaseTest() {

    private val authHelper = AuthHelper()

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
}