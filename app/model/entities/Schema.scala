package model.entities

case class Schema(name: String, tables: Map[String, Table], views: Seq[View], indices: Seq[Index]) {

}
