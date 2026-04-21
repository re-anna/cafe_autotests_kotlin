package org.example.backend.api.endpoints


import org.example.backend.api.models.LoginRequest
import org.example.backend.api.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthEndpoints {
    @POST("auth/login")
    fun login(@Body body: LoginRequest) : Call<LoginResponse>
}