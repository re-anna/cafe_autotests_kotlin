package database

import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject
import frontend.components.popup.CreateUserPopup
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import infra.junit.TestContext.token
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import kotlin.random.Random

@Tag("database")
class DBUserTest: BaseUiTest() {

    private val jdbcClient = JDBCHelper()
    private val controllers = Controllers()

    @Test
    @DisplayName("Check user is created via frontend and then exists in database -> jdbc")
    fun createUser(){

        val username = "default"
        val email = "random-${Random.nextInt(50000)}@autotest.com"
        val pass = "password"

        MainPage()
            .open()
            .header()
            .clickLink("Join")

        CreateUserPopup()
            .fillCreateAccount(username,email,pass)
            .clickCreateUserBtn()

        sleep(20000)

        val dbUser = jdbcClient.findUserByEmail(email)

        dbUser.shouldNotBeNull()
        dbUser.username shouldBe username
        dbUser.email shouldBe email

        val apiUser = controllers.users.getUserById(token, id = dbUser.id).getAsObject()

        dbUser.username shouldBe apiUser.username
        dbUser.email shouldBe apiUser.email
    }
}