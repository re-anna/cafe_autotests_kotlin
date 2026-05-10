package backend.api.models.users

import kotlin.random.Random

data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String
)

fun randomUser() = CreateUserRequest(
    username = "random",
    password = "user",
    email = "random-${Random.nextInt(10000)}@autotest.com"
)
