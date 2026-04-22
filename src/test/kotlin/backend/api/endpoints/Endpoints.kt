package backend.api.endpoints

import infra.RetrofitClient

open class Endpoints {
    protected val auth: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
}