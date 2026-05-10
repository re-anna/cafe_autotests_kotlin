package backend.test

import backend.api.models.ErrorResponse
import backend.controllers.Controllers
import backend.extension.ResponseExt.getAsObject
import backend.extension.ResponseExt.getErrorAsObject
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")
class LoginTest : Controllers() {

    @Test
    @DisplayName("Auth: positive login")
    fun loginPositive() {
        val response = auth.login(System.getProperty("admin.email"), System.getProperty("admin.password")).getAsObject()
        response.accessToken.length shouldBeGreaterThan 10
        response.refreshToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Auth: negative login")
    fun loginNegative() {
        val error = auth.login("invalid", "credentials").getErrorAsObject<ErrorResponse>()
        error.code shouldBe 400
        error.reason shouldBe "Invalid email or password"
    }
}