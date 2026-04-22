package backend.controllers

import io.qameta.allure.Step
import backend.api.endpoints.Endpoints
import backend.api.models.LoginRequest
import backend.api.models.LoginResponse
import retrofit2.Response

class AuthController : Endpoints(){

    @Step("Login: {email}")
    fun login(email: String, password: String): Response<LoginResponse> =
        auth.login(LoginRequest(email, password)).execute()
}