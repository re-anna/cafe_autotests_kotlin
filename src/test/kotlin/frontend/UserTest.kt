package frontend

import backend.api.models.users.randomUser
import frontend.components.HeaderComponent
import frontend.components.popup.CreateUserPopup
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.random.Random

class UserTest: BaseUiTest() {

    @Test
    @DisplayName("Create user with valid data")
    fun checkValidUserCreation(){
        val username = "default"
        val email = "random-${Random.nextInt(10000)}@autotest.com"
        val pass = "password"

        HeaderComponent().clickLink("Join")

        CreateUserPopup().fillCreateAccount(username,email,pass)
            .clickCreateUserBtn()

        val expected =listOf("Brew & Bean", "Products", "Orders", "Contact","Cart", "Logout")
        val actual = MainPage().open().header().getLinksText()

        actual shouldBe expected
    }

    @Test
    @DisplayName("Create user with null data")
    fun checkNullUserCreation(){
        val username = ""
        val email = ""
        val pass = ""

        HeaderComponent().clickLink("Join")

        CreateUserPopup().fillCreateAccount(username,email,pass)
            .clickCreateUserBtn()

        val expectedErrorText = "Please enter username, email and password"
        val actualErrorText = CreateUserPopup().getErrorText()

        expectedErrorText shouldBeEqual actualErrorText
    }
}