package backend.controllers

import io.qameta.allure.Step
import backend.api.endpoints.Endpoints
import backend.api.models.auth.LoginRequest

class AuthController : Endpoints(){

    @Step("Login with email: {email} and password: {password}")
    fun login(email: String, password: String) = auth.postLogin(body = LoginRequest(email = email, password = password))
        .execute()
}