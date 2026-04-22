package backend.api.endpoints


import backend.api.models.LoginRequest
import backend.api.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthEndpoints {
    @POST("auth/login")
    fun login(@Body body: LoginRequest) : Call<LoginResponse>
}