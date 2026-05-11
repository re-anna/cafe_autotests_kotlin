package backend.helpers

import backend.api.models.products.CreateProductsRequest
import backend.api.models.products.CreateProductsResponse
import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject
import io.qameta.allure.Step

class ProductsHelper : Controllers() {

    @Step("Create number of products {number}")
    fun createProducts(count: Int): List<CreateProductsResponse> {
        val listOfProducts = mutableListOf<CreateProductsResponse>()

        repeat(count) {index ->
            listOfProducts.add(
                products.createProduct(
                    product = CreateProductsRequest(
                        "Product #$index",
                        index+1.toDouble(),
                        "Description for product #$index"
                    )
                ).getAsObject()
            )
        }
        return listOfProducts.toList()
    }


    @Step("Ensure that at least {minCount} products with {word} exists")
    fun ensureProductsWithWordExists(word: String, minCount: Int): List<CreateProductsResponse> {
        val existing = products.getAllProducts().getAsObject().filter {
            it.name.contains(word, ignoreCase = true)
        }

        val needCreate = (minCount - existing.size)
        if (needCreate == 0) return emptyList()

        val created = mutableListOf<CreateProductsResponse>()
        repeat(needCreate){i ->
            val req = CreateProductsRequest(
                name = "${word} product #$i",
                price = 1.0+i,
                description = "Auto created $word product"
            )
            created += products.createProduct(product = req).getAsObject()
        }
        return created
    }

    @Step("Count products with '{word}' on backend")
    fun countProductsWithWordBackend(word: String): Int =
        products.getAllProducts().getAsObject().count{it.name.contains(word, ignoreCase = true)}
}