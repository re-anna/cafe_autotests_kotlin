package infra.junit

import backend.api.models.users.CreateUserResponse

object TestContext {
    var user: CreateUserResponse? = null
    var  token:  String? = null
}