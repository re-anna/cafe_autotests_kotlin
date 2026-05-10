package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.endpoints.exstension.Exstension.Companion.getAsObject
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateRequest
import backend.extension.ResponseExt.getAsObject
import backend.helpers.AuthHelper
import backend.helpers.GarbageCollector
import com.codeborne.selenide.Condition.id
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class UsersController : Endpoints() {
    private val authHelper = AuthHelper()

    @Step("Get all users")
    fun getAllUsers(token: String = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 50): Response<List<CreateUserResponse>> {
        return users.getUsers(token, offset, limit).execute()
    }

    @Step("Create user")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
        return users.createUser(body).execute()
            .also { GarbageCollector.user.add(it.getAsObject().id}
    }

    @Step("Get user by id={id}")
    fun getUserById(token: String = authHelper.getAdminToken(), id: Int): Response<CreateUserResponse> {
        return users.getUserById(token, id).execute()
    }

    @Step("Update user by id={id}")
    fun updateUserById(token: String = authHelper.getAdminToken(), id: Int, body: UpdateRequest): Response<CreateUserResponse> {
        return users.putUserById(token, id, body).execute()
    }

    @Step("Delete user by id={id}")
    fun deleteUserById(token: String = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
        return users.deleteUserById(token, id).execute()
    }
}