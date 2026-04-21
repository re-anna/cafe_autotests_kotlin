package org.example.backend.controllers

import io.qameta.allure.Step
import org.example.backend.api.endpoints.AuthEndpoints
import org.example.backend.api.models.LoginRequest
import org.example.backend.api.models.LoginResponse
import org.example.infra.RetrofitClient
import retrofit2.Response

class AuthController {
    private val api: AuthEndpoints by lazy { RetrofitClient.create(AuthEndpoints::class.java) }

    @Step("Login: {email}")
    fun login(email: String, password: String): Response<LoginResponse> =
        api.login(LoginRequest(email,password)).execute()
}