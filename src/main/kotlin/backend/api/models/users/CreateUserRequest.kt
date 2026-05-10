package backend.api.models.users

import kotlin.random.Random

data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String
)

val defaultUser: CreateUserRequest
    get() = CreateUserRequest(
        username = "random",
        password = "random",
        email = "auto-${Random.nextInt(10000)}@autotest.com"
    )
