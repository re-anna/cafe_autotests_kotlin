package frontend.e2e

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import infra.junit.TestContext
import infra.junit.TestContext.token
import io.kotest.matchers.ints.shouldBeGreaterThan
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("e2e")
class OrdersE2ETest : BaseUiTest() {

    @Test
    @DisplayName("Create order via UI and verify via backend")
    fun createOrderUiBack(){

        val ordersBefore = orders.getOrderByUserId(
            token,
            TestContext.user.id
        )

        val addFirstProduct = MainPage()
            .open()
            .getPopularProducts()


        MainPage()
            .open()
            .header()
            .clickLink("Cart")
            .openCartPopup()
            .createOrder()

        val ordersAfter = orders.getOrderByUserId(
            token,
            TestContext.user.id
        )
        ordersAfter.size shouldBeGreaterThan ordersBefore.size
    }


}