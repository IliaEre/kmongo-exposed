package dsl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import repo.City
import repo.User

const val url: String = "jdbc:h2:mem:test"
const val driver: String = "org.h2.Driver"
const val username: String = "root"
const val password: String = ""

fun main() {
    Database.connect(url, driver, username, password)

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create (City, User)

        // insert cities
        val saintPetersburgId = City.insert {
            it[name] = "St. Petersburg"
        } get City.id

        val munichId = City.insert {
            it[name] = "Munich"
        } get City.id

        val pragueId = City.insert {
            it.update(name, stringLiteral("   Prague   ").trim().substring(1, 2))
        }[City.id]

        // insert users
        User.insert {
            it[id] = "andrey"
            it[name] = "Andrey"
            it[User.cityId] = saintPetersburgId
        }

        User.insert {
            it[id] = "sergey"
            it[name] = "Sergey"
            it[User.cityId] = munichId
        }

        User.insert {
            it[id] = "eugene"
            it[name] = "Eugene"
            it[User.cityId] = munichId
        }

        User.insert {
            it[id] = "alex"
            it[name] = "Alex"
            it[User.cityId] = null
        }

        User.insert {
            it[id] = "smth"
            it[name] = "Something"
            it[User.cityId] = null
        }

        User.update({ User.id eq "alex"}) {
            it[name] = "Alexey"
        }

        User.deleteWhere{ User.name like "%thing"}
    }
}