package database

import frontend.helpers.BaseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DBProductsTest: BaseTest() {
    val jdbcClient = JDBCHelper()

    //todo пустой массив
    @Test
    @DisplayName("Fetching all products from database  -> basic JDBC")
    fun testGetAllProducts(){
        val products = jdbcClient.getProducts()
        println(products)
    }

    @Test
    @DisplayName("Test fetching all products from the database -> Kotlin JDBC")
    fun testGetAllProductsKotlin() {
        val products = jdbcClient.getProductNew()
        println(products)
        assertEquals(3, products.size)
    }

    @Test
    @DisplayName("Test fetching all products from the database -> Exposed ORM")
    fun testGetAllProductsExposed() {
        val jdbcClientExposed = ExposedHelper()

        val products = jdbcClientExposed.getAllProductsExposed()
        assertEquals(3, products.size)
    }
}