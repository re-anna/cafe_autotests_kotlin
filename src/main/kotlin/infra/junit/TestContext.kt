package infra.junit

import backend.api.models.users.CreateUserResponse

data class Credentials(
    val email: String,
    val password: String
)

object TestContext {

    private var _user: CreateUserResponse? = null
    private var _token: String? = null
    private var _creds: Credentials? = null


    val user: CreateUserResponse
        get() = requireNotNull(_user) {
            "TestContext.user is null. Проверь, что тест наследуется от BaseTest и UsersForTestExt отрабатывает."
        }

    val token: String
        get() = requireNotNull(_token) {
            "TestContext.token is null. Проверь, что UsersForTestExt создал токен."
        }

    val creds: Credentials
        get() = requireNotNull(_creds) {
            "TestContext.creds is null. Проверь UsersForTestExt."
        }

    fun set(user: CreateUserResponse, token: String, creds: Credentials) {
        _user = user
        _token = token
        _creds = creds
    }

    fun clear() {
        _user = null
        _token = null
        _creds = null
    }
}