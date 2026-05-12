package frontend.helpers

import frontend.components.popup.CreateUserPopup
import frontend.components.popup.LogInPopup
import frontend.pages.MainPage
import infra.junit.TestContext
import io.kotest.matchers.collections.shouldContain

object AuthHelperUI {

    fun loginAsCurrentTestUser(): MainPage{
        MainPage()
            .open()
            .header()
            .clickLink("Join")

        CreateUserPopup()
            .clickLoginLink()

        LogInPopup()
            .putEmailAndPassword(TestContext.creds.email, TestContext.creds.password)
            .clickLoginBtn()

        val link = MainPage().header().getLinksText()
        link shouldContain "Logout"

        return MainPage()
    }
}