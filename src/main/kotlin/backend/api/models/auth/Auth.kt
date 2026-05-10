package backend.api.models.auth

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val id: Int,
    val accessToken: String,
    val refreshToken: String,
    val createdAt: Long,
    val expireInMs: Long
)

val defaultAdmin = LoginRequest(
    email = System.getProperty("admin.email","__ADMIN_EMAIL_"),
    password = System.getProperty("admin.password","__ADMIN_PASSWORD__")
)