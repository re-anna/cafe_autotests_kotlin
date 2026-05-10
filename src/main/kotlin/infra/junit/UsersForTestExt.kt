package infra.junit


import backend.api.models.users.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.getAsObject
import backend.helpers.AuthHelper
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class UsersForTestExt: Controllers(), BeforeEachCallback {
    private val  authHelper = AuthHelper()

    override fun beforeEach(context: ExtensionContext?) {
        val newUser = users.createUser(randomUser()).getAsObject()
        val token = authHelper.getAuthorizationToken(email = newUser.email, password = "user")

        TestContext.user = newUser
        TestContext.token = token
    }
}