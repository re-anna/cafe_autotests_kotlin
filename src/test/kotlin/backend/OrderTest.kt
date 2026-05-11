package backend

import backend.api.models.products.defaultProduct
import backend.controllers.Controllers
import infra.junit.TestContext.token
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")
class OrderTest: Controllers() {

    @Test
    @DisplayName("Check creation of valid order")
    fun createValidOrder(){
        val order = orders.createOrder(
            token,
            defaultProduct()
        )
        println(order)
    }

}