package frontend.components.popup

import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.byDataTestId
import io.qameta.allure.Step

class CreateUserPopup {

    private val usernameInput: SelenideElement
        get() =element(byDataTestId("create-username"))
        .find(shadowCss("input"))
    private val emailInput: SelenideElement get() =element(byDataTestId("create-email"))
        .find(shadowCss("input"))
    private val passwordInput: SelenideElement get() =element(byDataTestId("create-password"))
        .find(shadowCss("input"))
    private val createUserBtn: SelenideElement get() =element(byDataTestId("create-submit"))
    private val createUserError: SelenideElement get() = element(byDataTestId("create-error"))
    private val createLogIn: SelenideElement get() = element(byDataTestId("create-login"))

    @Step("Fill form Create Account")
    fun fillCreateAccount(username: String, email: String, pass: String): CreateUserPopup {
        usernameInput.value = username
        emailInput.value = email
        passwordInput.value = pass
        return this
    }

    @Step("Click Create User button")
    fun clickCreateUserBtn(): CreateUserPopup {
        createUserBtn.click()
        return this
    }

    @Step("Get create user error text")
    fun getErrorText(): String = createUserError.text()

    @Step("Go to login popup")
    fun clickLoginLink(): CreateUserPopup{
        createLogIn.click()
        return this
    }
}