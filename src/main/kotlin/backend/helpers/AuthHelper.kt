package backend.helpers

import backend.api.models.auth.defaultAdmin
import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject
import backend.api.extension.ResponseExt.toBearer
import infra.junit.TestContext
import io.qameta.allure.Step
import org.junit.jupiter.api.DisplayName

class AuthHelper: Controllers() {

    fun getDefaultToken(): String = TestContext.token

    @Step
    @DisplayName("Get authorization token")
    fun getAuthorizationToken(email: String, password: String): String {
        return auth.login(email,password).getAsObject().accessToken.toBearer()
    }

}