package backend.api.endpoints

import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserEndpoints {

    @GET("users/")
    fun getUsers(
        @Header(Headers.AUTHORIZATION) token: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<CreateUserResponse>>

    @POST("users/create")
    fun createUser(@Body body: CreateUserRequest): Call<CreateUserResponse>

    @GET("users/{id}")
    fun getUserById(
        @Header(Headers.AUTHORIZATION) token: String?,
        @Path("id") id: Int
    ): Call<CreateUserResponse>

    @PUT("users/{id}")
    fun putUserById(
        @Header(Headers.AUTHORIZATION) token: String?,
        @Path("id") id: Int,
        @Body body: UpdateRequest
    ): Call<CreateUserResponse>

    @DELETE("users/{id}")
    fun deleteUserById(
        @Header(Headers.AUTHORIZATION) token: String?,
        @Path("id") id: Int
    ): Call<ResponseBody>
}
