package frontend.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.urlContaining
import frontend.components.popup.CartPopup
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.pages.ProductsPage
import io.qameta.allure.Step

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

    @Step("Click on {name} link and check link")
    fun clickLinkAndWaitUrl(name: String, expectedUrl: String): HeaderComponent{
        clickLink(name)
        webdriver().shouldHave(urlContaining(expectedUrl))
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