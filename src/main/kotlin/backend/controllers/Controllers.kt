package backend.controllers

open class Controllers {
    val auth by lazy {AuthController() }
    val users by lazy {UsersController() }
    val products by lazy {ProductController() }
    val orders by lazy {OrderController() }
}