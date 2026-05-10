package backend.helpers

import backend.api.models.auth.defaultAdmin
import backend.controllers.Controllers
import backend.extension.ResponseExt.getAsObject
import backend.extension.ResponseExt.toBearer
import io.qameta.allure.Step
import org.junit.jupiter.api.DisplayName

class AuthHelper: Controllers() {

    @Step
    @DisplayName("Get authorization token")
    fun getAuthorizationToken(email: String, password: String): String {
        return auth.login(email,password).getAsObject().accessToken.toBearer()
    }

    @Step
    @DisplayName("Get ADMIN token")
    fun getAdminToken(): String {
        return auth.login(email = defaultAdmin.email, password = defaultAdmin.password).getAsObject().accessToken.toBearer()
    }
}