package backend.api.models

data class ErrorResponse(
    var code: Int,
    var reason: String
)
        val invalidCredentials = ErrorResponse(
            code = 400,
            reason = "Invalid email or password"
        )

        val emptyCredentials = ErrorResponse(
            code = 400,
            reason = "Invalid email or password"
        )

        val userAlreadyExists = ErrorResponse(
            code = 400,
            reason = "Something went wrong. Please verify request."
        )

        val invalidToken = ErrorResponse(
            code = 401,
            reason = "The token is invalid."
        )

        val emptyProductListInOrders = ErrorResponse(
            code = 400,
            reason = "products cannot be null or empty"
        )
