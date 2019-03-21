package model.statements

import TableDefinitions._

case class CreateTableStatement(tableScope: Option[String], tableName: String, tableContents: TableContentsSource, onCommit: Option[TableCommitAction]) extends SQLStatement

object TableDefinitions {
  // The possible set of elements in a table's definition
  trait TableElement
  case class ColumnDefinition(columnName: String, columnType: DataType) extends TableElement
  //case class TableConstraintDefinition() extends TableElement
  //case class LikeClause() extends TableElement
  //case class SelfReferencingColumnSpecification() extends TableElement
  //case class ColumnOptions() extends TableElement

  // The possible set of definitions from which a table can be created
  trait TableContentsSource
  case class TableElementList(elements: Seq[TableElement]) extends TableContentsSource

  // The possible set of actions that can be performed when a table is committed
  trait TableCommitAction
  case object Delete extends TableCommitAction
  case object Preserve extends TableCommitAction
}





