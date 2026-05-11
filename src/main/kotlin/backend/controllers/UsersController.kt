package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateRequest
import backend.api.extension.ResponseExt.getAsObject
import backend.helpers.AuthHelper
import backend.helpers.GarbageCollector
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class UsersController : Endpoints() {
    private val authHelper = AuthHelper()

    @Step("Get all users")
    fun getAllUsers(token: String? = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 50): Response<List<CreateUserResponse>> {
        return users.getUsers(token, offset, limit).execute()
    }

    @Step("Create user")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
        val response =  users.createUser(body).execute()
            if(response.isSuccessful) { GarbageCollector.user.add(response.getAsObject().id)
            }
        return response
    }

    @Step("Get user by id={id}")
    fun getUserById(token: String? = authHelper.getAdminToken(), id: Int): Response<CreateUserResponse> {
        return users.getUserById(token, id).execute()
    }

    @Step("Update user by id={id}")
    fun updateUserById(token: String? = authHelper.getAdminToken(), id: Int, body: UpdateRequest): Response<CreateUserResponse> {
        return users.putUserById(token, id, body).execute()
    }

    @Step("Delete user by id={id}")
    fun deleteUserById(token: String? = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
        return users.deleteUserById(token, id).execute()
    }
}