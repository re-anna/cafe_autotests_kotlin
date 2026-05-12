package frontend.e2e

import OrderStatus
import backend.api.extension.ResponseExt.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.api.models.products.defaultProduct
import frontend.components.popup.CreateUserPopup
import frontend.components.popup.LogInPopup
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import infra.junit.TestContext
import infra.junit.TestContext.token
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

@Tag("e2e")
class OrdersE2ETest : BaseUiTest() {

    @ParameterizedTest
    @EnumSource(OrderStatus::class)
    @DisplayName("Parametrized order status check")
    fun changeOrderStatus(status: OrderStatus){
        val product = products.createProduct(
            token = token,
            product = defaultProduct()
        ).getAsObject()

        val order = orders.createOrder(
            token = token,
            body = CreateOrderRequest(
                TestContext.user.id,
                listOf(CreateOrderRequest.Product(id = product.id))
            )
        )

        val update = orders.updateOrderById(token,order.id, UpdateOrderStatusRequest(status.value))
        update.orderStatus shouldBe status.value

        MainPage()
            .open()
            .header()
            .clickLink("Join")
        CreateUserPopup()
            //todo отдебажить
            .clickLoginLink()
        //нужен хелпер или контроллер чтобы прям разом логиниться и куда-то код засунуть т к каждый раз будет открываться
        LogInPopup()
            .putEmailAndPassword(TestContext.creds.email, TestContext.creds.password)
            .clickLoginBtn()

        val uiOrder = OrdersPage().open().findOrder(order.id)
        //как сравнить модели статус
        uiOrder.status shouldBe status.value

    }

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
            .first()
        addFirstProduct.btnIncrement!!.click()


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

        //ordersAfter.products.map {}

    }


}