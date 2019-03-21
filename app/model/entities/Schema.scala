package model.entities

case class Schema(name: String, tables: Map[String, Table], views: Map[String, View], indices: Map[String, Index]) {

}
