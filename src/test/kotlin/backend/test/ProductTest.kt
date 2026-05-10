package backend.test

import backend.api.endpoints.exstension.Exstension.Companion.getAsObject
import backend.api.models.products.defaultProduct
import backend.controllers.Controllers
import backend.helpers.AuthHelper
import infra.junit.TestContext.token
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductTest: Controllers() {

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