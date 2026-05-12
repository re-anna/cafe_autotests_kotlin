package infra.junit


import backend.api.models.users.randomUser
import backend.controllers.Controllers
import backend.api.extension.ResponseExt.getAsObject
import backend.helpers.AuthHelper
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class UsersForTestExt: Controllers(), BeforeEachCallback {
    private val  authHelper = AuthHelper()

    override fun beforeEach(context: ExtensionContext?) {
        val req = randomUser()
        val newUser = users.createUser(req).getAsObject()
        val token = authHelper.getAuthorizationToken(req.email, req.password)

        TestContext.set(
            newUser,
            token,
            creds = Credentials(req.email,req.password))
    }
}