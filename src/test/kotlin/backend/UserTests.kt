package backend

import backend.api.models.ErrorResponse
import backend.api.models.userAlreadyExists
import backend.api.models.users.UpdateRequest
import backend.api.models.users.randomUser
import backend.api.extension.ResponseExt.checkIsSuccessful
import backend.api.extension.ResponseExt.getAsObject
import backend.api.extension.ResponseExt.getErrorAsObject
import frontend.helpers.BaseTest
import infra.junit.TestContext.token
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")
class UserTests: BaseTest() {

    @Test
    @DisplayName("Create user with valid data")
    fun createUser(){
        val actualUser = users.createUser(randomUser()).getAsObject()
        val expectedUser = users.getUserById(token = token, id = actualUser.id).getAsObject()

        expectedUser shouldBeEqual actualUser
    }

    @Test
    @DisplayName("Delete valid user should return 200")
    fun deleteDefaultUser(){
        val actualUser = users.createUser(randomUser()).getAsObject()
        val delete = users.deleteUserById(token = token, id = actualUser.id)

        delete.code() shouldBe 200
    }

    @Test
    @DisplayName("Update phone number")
    fun updatePhoneNumber(){
        val createdUser = users.createUser(randomUser()).getAsObject()
        val newPhone = "89998988998"

        users.updateUserById(token,createdUser.id, UpdateRequest(phoneNumber = newPhone)).getAsObject()

        val updatedUser = users.getUserById(token,createdUser.id).getAsObject()

        updatedUser.phoneNumber shouldBeEqual newPhone
    }

    @Test
    @DisplayName("Update full user model with valid data")
    fun updateFullUserData(){
        val newUser = users.createUser(randomUser()).getAsObject()
        val updateRequest = UpdateRequest(
            "updated-${newUser.username}",
            "updated-password",
            "updated-${newUser.email}",
            "899888888}"
        )

        val updatedUser = users.updateUserById(token, id = newUser.id, body = updateRequest).getAsObject()
        val login = auth.login(updateRequest.email!!, updateRequest.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
        updatedUser.phoneNumber shouldBe updateRequest.phoneNumber
        updatedUser.username shouldBe updateRequest.username
        updatedUser.email shouldBe updateRequest.email
    }

    @Test
    @DisplayName("Update partial user model with valid data")
    fun updatePartialUserData(){
        val newUser = users.createUser(randomUser()).getAsObject()
        val updateRequest = UpdateRequest( password ="UpdatePassword")
        users.updateUserById(token,newUser.id,updateRequest).checkIsSuccessful()

        val login = auth.login(newUser.email, updateRequest.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Error: Create same user 2 times")
    fun checkDuplicationUserCreationError(){
        val newUser = randomUser()
        users.createUser(newUser).getAsObject()

        val error = users.createUser(newUser).getErrorAsObject<ErrorResponse>()

        error shouldBe userAlreadyExists
    }
}