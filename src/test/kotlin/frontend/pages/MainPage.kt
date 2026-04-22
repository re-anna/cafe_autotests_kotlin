package frontend.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import frontend.components.HeaderComponent
import frontend.helpers.Wrappers.byDataTestId
import io.kotest.matchers.shouldBe
import io.qameta.allure.Step

class MainPage {
    private val txtTitle: SelenideElement get() = element(byDataTestId("main-image-text"))

    @Step("Open Main Page")
    fun open(): MainPage {
        Selenide.open("/")
        return this
    }

    @Step("Get name of CoffeePlace")
    fun getTitle(): String = txtTitle.text.trim()


    @Step("Check Title")
    fun shouldHaveTitle(expected: String = "Welcome to Brew & Bean"): MainPage {
        txtTitle.shouldBe(visible)
        txtTitle.text.trim() shouldBe expected
        return this
    }

    @Step("Go to Header Component")
    fun navigateHeader(): HeaderComponent = HeaderComponent()
}