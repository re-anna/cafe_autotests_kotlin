package backend

import backend.api.models.users.randomUser
import backend.api.extension.ResponseExt.getAsObject
import frontend.helpers.BaseTest
import infra.junit.TestContext.token
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")
class GetAllUsersTest: BaseTest() {

    @Test
    @DisplayName("Check creation of user backend")
    fun getUserFromAllUsers(){
        val newUser = users.createUser(randomUser()).getAsObject()
        val allUsers = users.getUserById(token, newUser.id).getAsObject()

        allUsers shouldBe newUser
    }
}