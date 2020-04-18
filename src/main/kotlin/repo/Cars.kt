package repo

import org.jetbrains.exposed.dao.id.IntIdTable

object Cars: IntIdTable() {
    val name = varchar("descr", 25)
}