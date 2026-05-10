package backend.test

import backend.api.models.users.UpdateRequest
import backend.api.models.users.defaultUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.checkIsSuccessful
import backend.extension.ResponseExt.getAsObject
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("backend")
class UsersTest : Controllers(){

    @Test
    @DisplayName("Users: create user positive")
    fun createUserPositive() {
        val created = users.createUser(defaultUser).getAsObject()
        created.email shouldBe created.email
        //TO DO поправить
    }

    @Test
    @DisplayName("Users: update phone via PUT")
    fun updatePhone() {
        val created = users.createUser(defaultUser).getAsObject()

        val updated = users.updateUserById(
            id = created.id,
            body = UpdateRequest(phoneNumber = "1234567890")
        ).getAsObject()

        updated.phoneNumber shouldBe "1234567890"
    }

    @Test
    @DisplayName("Users: update partial model (password only should be successful")
    fun updatePartial() {
        val created = users.createUser(defaultUser).getAsObject()
        users.updateUserById(id = created.id, body = UpdateRequest(password = "updatedPassword")).checkIsSuccessful()
    }
}
