package kmongo

import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import repo.Jedi

fun main() {
    val kmongo = KMongo.createClient() // create new instance of kmongo
    val database = kmongo.getDatabase("test")
    val col = database.getCollection<Jedi>()
    col.insertOne(Jedi("Luke Skywalker", 19))

    val yoda : Jedi? = col.findOne(Jedi::name eq "Yoda")
    if (yoda != null) println("Yoda is here!") else println("None")
}