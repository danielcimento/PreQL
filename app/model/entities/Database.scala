package model.entities

case class Database(name: String, schemas: Map[String, Schema]) {

}

object Database {
  def emptyDatabase(name: String) = Database(name, Map("PUBLIC" -> Schema("PUBLIC", Map(), Map(), Map())))
}