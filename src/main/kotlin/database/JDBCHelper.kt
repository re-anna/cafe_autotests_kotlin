package database

import config.Config
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class JDBCHelper {

    private val client = DriverManager.getConnection(
        Config.get.dbUrl,
        Config.get.dbUsername,
        Config.get.dbPassword
        )

    fun deleteOrderById(orderId: Int): Int =
        client.use { connection ->
            connection.prepareStatement("DELETE FROM table orders WHERE id = ?").use {
                statement ->
                statement.setInt(1, orderId)
                statement.executeUpdate()
            }
        }

    fun getProducts(): List<Product> {
        val products = mutableListOf<Product>()

        try {
            val statement: Statement = client.createStatement()
            val resultSet: ResultSet = statement.executeQuery("SELECT * FROM table_products")

            while (resultSet.next()) {
                val product = Product(
                    id = resultSet.getInt("id"),
                    name = resultSet.getString("name"),
                    description = resultSet.getString("description"),
                    price = resultSet.getDouble("price")
                )
                products.add(product)
            }
            resultSet.close()
            statement.close()
        } catch (e: Exception) {
            println("Error fetching products ${e.message}")
        }
        return products
    }

    fun getProductNew() = client.use { connection ->
        connection.createStatement().use { statement ->
            statement.executeQuery("SELECT * FROM table_products").use { resultSet ->
                generateSequence { resultSet.takeIf { it.next() }?.toProduct() }.toList()
            }
        }
    }

    fun getUsers() = client.use { connection ->
        connection.createStatement().use { statement ->
            statement.executeQuery("SELECT * FROM table_users").use { resultSet ->
                generateSequence { resultSet.takeIf { it.next() }?.toUsers() }.toList()
            }
        }
    }

    fun findUserByEmail(email: String): Users? {
        val sql = """
            SELECT id, username, email
            FROM table_users
            Where email = ?
        """.trimIndent()

        return client.use { connection ->
            connection.prepareStatement(sql).use { preparedStatement ->
                preparedStatement.setString(1,email)

                preparedStatement.executeQuery().use { resultSet ->
                    if (!resultSet.next()) return null

                    Users(
                        id = resultSet.getInt("id"),
                        username = resultSet.getString("username"),
                        email = resultSet.getString("email")
                    )
                }
            }
        }
    }
}


fun ResultSet.toProduct(): Product = Product(
    id = getInt("id"),
    name = getString("name"),
    description = getString("description"),
    price = getDouble("price")
)

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String
)

fun ResultSet.toUsers(): Users = Users(
    id = getInt("id"),
    username = getString("username"),
    email = getString("email"),
)

data class Users(
    var id: Int,
    var username: String,
    var email: String
)
