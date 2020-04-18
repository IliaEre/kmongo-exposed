package repo

import org.jetbrains.exposed.sql.Table

object City: Table() {
    val id = integer("id").autoIncrement() // Column<Int>
    val name = varchar("name", 50) // Column<String>
}