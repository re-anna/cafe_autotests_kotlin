package infra.junit

import backend.api.models.users.createUser.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import backend.helpers.AuthHelper
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class UsersForTestExt: Controllers(), BeforeEachCallback {
    private val  authHelper = AuthHelper()

    override fun beforeEach(context: ExtensionContext?) {
        val newUser = user.createUser(randomUser()).getAsObject()
        val token = authHelper.getAuthorizationToken(email = newUser.email, password = "user")

        TestContext.user = newUser
        TestContext.token = token
    }
}