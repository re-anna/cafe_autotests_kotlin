package database

import frontend.helpers.BaseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("database")
class DBProductsTest: BaseTest() {
    val jdbcClient = JDBCHelper()

    @Test
    @DisplayName("Test fetching all products from the database -> Kotlin JDBC")
    fun testGetAllProductsKotlin() {
        val products = jdbcClient.getProductNew()
        println(products)
        assertEquals(3, products.size)
    }
}