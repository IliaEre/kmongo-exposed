package repo

import org.jetbrains.exposed.sql.Table

object User : Table() {
    val id = varchar("id", 10) // Column<String>
    val name = varchar("name", length = 50) // Column<String>
    val cityId = (integer("city_id") references City.id).nullable() // Column<Int?>
}