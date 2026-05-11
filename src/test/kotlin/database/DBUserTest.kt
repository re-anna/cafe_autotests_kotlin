package database

import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject
import backend.helpers.GarbageCollector
import frontend.components.popup.CreateUserPopup
import frontend.helpers.BaseTest
import frontend.pages.MainPage
import infra.junit.TestContext.token
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.random.Random

@Tag("database")
class DBUserTest: BaseTest() {

    private val jdbcClient = JDBCHelper()
    private val controllers = Controllers()

    @Test
    @DisplayName("Check user is created via frontend and then exists in database -> jdbc")
    fun createUserKotlin(){

        val username = "default"
        val email = "random-${Random.nextInt(10000)}@autotest.com"
        val pass = "password"

        MainPage()
            .open()
            .header()
            .clickLink("Join")

        CreateUserPopup()
            .fillCreateAccount(username,email,pass)
            .clickCreateUserBtn()

        val dbUser = jdbcClient.findUserByEmail(email)
        dbUser.shouldNotBeNull()

        GarbageCollector.user.add(dbUser.id)

        dbUser.username shouldBe username
        dbUser.email shouldBe email

        val apiUser = controllers.users.getUserById(token, id = dbUser.id).getAsObject()

        dbUser.username shouldBe apiUser.username
        dbUser.email shouldBe apiUser.email
    }
}