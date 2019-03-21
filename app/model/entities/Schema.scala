package model.entities

case class Schema(name: String, tables: Seq[Table], views: Seq[View], indices: Seq[Index]) {

}
