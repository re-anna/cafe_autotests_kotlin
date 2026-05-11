package infra.junit

import backend.api.models.users.CreateUserResponse

object TestContext {

    private var _user: CreateUserResponse? = null
    private var _token: String? = null

    val user: CreateUserResponse
        get() = requireNotNull(_user) {
            "TestContext.user is null. Проверь, что тест наследуется от BaseTest и UsersForTestExt отрабатывает."
        }

    val token: String
        get() = requireNotNull(_token) {
            "TestContext.token is null. Проверь, что UsersForTestExt создал токен."
        }

    fun set(user: CreateUserResponse, token: String) {
        _user = user
        _token = token
    }

    fun clear() {
        _user = null
        _token = null
    }
}