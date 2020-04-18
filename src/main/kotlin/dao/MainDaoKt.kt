package dao

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import repo.Car
import repo.Cars

fun main() {
    Database.connect(dsl.url, dsl.driver, dsl.username, dsl.password)

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Cars)

        val car = Car.new {
            name = "BWM"
        }
        println("Cars: ${Car.all()}")
    }

}