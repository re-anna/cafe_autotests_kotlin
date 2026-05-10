package backend.api.endpoints

import infra.RetrofitClient
import kotlin.jvm.java

open class Endpoints {
    protected val auth: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
    protected val users: UserEndpoints by lazy { RetrofitClient.createService(UserEndpoints::class.java) }
    protected val products: ProductsEndpoints by lazy { RetrofitClient.createService(ProductsEndpoints::class.java) }
}