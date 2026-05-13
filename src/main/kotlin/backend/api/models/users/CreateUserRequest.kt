package backend.api.models.users

import java.util.UUID

data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String
)

fun randomUser() = CreateUserRequest(
    username = "random",
    password = "user",
    email = "random-${System.currentTimeMillis()}-${UUID.randomUUID()}@autotest.com"
)
