package frontend.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.WebDriverConditions.urlContaining
import frontend.components.popup.CartPopup
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.pages.ProductsPage
import io.qameta.allure.Step
import java.util.concurrent.locks.Condition
import kotlin.coroutines.Continuation

class HeaderComponent {
    private val links get() = elements(byDataTestGroup("nav-link"))

    fun getLinksText(): List<String> = links.map {it.text()}

    @Step("Click on {name} link")
    fun clickLink(name: String):HeaderComponent{
        links.first { it.text().contains(name) }
            .shouldBe(visible)
            .click()
        return this
    }

    @Step("Go to Products page")
    fun goToProducts(): ProductsPage {
        clickLink("Products")
        return ProductsPage()
    }

    @Step("Get cart popup")
    fun openCartPopup(): CartPopup =
        CartPopup()


}