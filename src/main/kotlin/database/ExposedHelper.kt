package database

import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction


private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
private val username: String = "postgres"
private val password: String = "postgres"

class ExposedHelper {

    fun getAllProductsExposed(): List<Product> {
        val database = Database.connect(
            url = jdbcUrl,
            driver = "org.postgresql.Driver",
            user = username,
            password = password
        )

        return transaction(database) {
            ProductEntity
                .selectAll()
                .map { ProductEntity.toModel(it) }
        }.also { database.connector().close() }
    }

    fun getAllUsersExposed(): List<Users> {
        val database = Database.connect(
            url = jdbcUrl,
            driver = "org.postgresql.Driver",
            user = username,
            password = password
        )

        return transaction(database) {
            UsersEntity.selectAll().map { UsersEntity.toModel(it) }
        }.also { database.connector().close() }
    }

    fun findUserByEmail(email: String): Users? {
        val database = Database.connect(
            url = jdbcUrl,
            driver = "org.postgresql.Driver",
            user = username,
            password = password
        )

        return transaction(database) {
            UsersEntity
                .selectAll()
                .where{UsersEntity.email eq email}
                .limit(1)
                .map { UsersEntity.toModel(it)}
                .singleOrNull()
        }.also { database.connector().close() }
    }
}


object ProductEntity: IntIdTable("table_products") {
    var Name = varchar("name", 100)
    var Description = varchar("description", 255)
    var Price = double("price")
}

fun ProductEntity.toModel(resultRow: ResultRow) = Product(
    id = resultRow[id].value,
    name = resultRow[ProductEntity.Name],
    description = resultRow[ProductEntity.Description],
    price = resultRow[ProductEntity.Price],
)


object UsersEntity: IntIdTable("table_users") {
    var username = varchar("username", 255)
    var password = varchar("password", 255)
    var email = varchar("email", 255)
}

fun UsersEntity.toModel(resultRow: ResultRow) = Users(
    id = resultRow[id].value,
    username = resultRow[UsersEntity.username],
    email = resultRow[UsersEntity.email],
)
