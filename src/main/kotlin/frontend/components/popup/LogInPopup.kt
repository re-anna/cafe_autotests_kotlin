package frontend.components.popup

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.byTestId
import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step


class LogInPopup{
    private val btnClose get() = element(byTestId("login-close"))
    private val txtTitle get() = element("title")
    private val inputEmail get() = element(byTestId("login-email")).find(shadowCss("input"))
    private val inputPass get() = element(byTestId("login-password")).find(shadowCss("input"))
    private val btnSubmitLogin get() = element(byTestId("login-submit"))
    private val txtError get() = element(byTestId("login-error"))

    @Step("Close popup")
    fun clickCloseButton(): LogInPopup{
        btnClose.click()
        return this
    }

    @Step("Get popup title")
    fun getPopupTitle(): String{
        return txtTitle.text
    }

    @Step("Put email and password")
    fun putEmailAndPassword(email: String, password: String): LogInPopup{
        inputEmail.value = email
        inputPass.value = password
        return this
    }

    @Step("Click login button")
    fun clickLoginBtn(): LogInPopup{
        btnSubmitLogin.click()
        return this
    }

    @Step("Get error text")
    fun getErrorText(): String{
        txtError.shouldBe(visible)
        return txtError.text
    }
}
