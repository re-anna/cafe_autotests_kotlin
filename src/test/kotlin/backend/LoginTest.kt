package backend

import backend.api.models.ErrorResponse
import backend.api.models.emptyCredentials
import backend.api.models.invalidCredentials
import backend.controllers.Controllers
import backend.extension.ResponseExt.getAsObject
import backend.extension.ResponseExt.getErrorAsObject
import frontend.helpers.BaseTest
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")

class LoginTest : BaseTest() {

    @Test
    @DisplayName("Login with valid credentials should return access token")
    fun testLoginWithValidCredentials() {
        val response = auth.login("random@test.com","password").getAsObject()

        response.accessToken.length shouldBeGreaterThan 10
        response.refreshToken.length shouldBeGreaterThan 10
        response.id shouldBeGreaterThan 0
    }

    @Test
    @DisplayName("Login with empty credentials")
    fun loginWithEmptyCredentials() {
        val response = auth.login("", "").getErrorAsObject<ErrorResponse>()
        response shouldBe emptyCredentials
    }

    @Test
    @DisplayName("Login with invalid credentials should return error")
    fun testLoginWithInvalidCredentials() {
        val response = auth.login("!!!invalid", "!!!invalid").getErrorAsObject<ErrorResponse>()

        response shouldBe invalidCredentials
    }
}