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