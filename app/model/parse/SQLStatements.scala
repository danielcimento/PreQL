package model.parse

trait SQLStatements

trait TableContentsSource

trait TableElement
case class ColumnDefinition(columnName: String, columnType: String) extends TableElement
//case class TableConstraintDefinition() extends TableElement
//case class LikeClause() extends TableElement
//case class SelfReferencingColumnSpecification() extends TableElement
//case class ColumnOptions() extends TableElement

case class TableElementList(elements: Seq[TableElement]) extends TableContentsSource

trait TableCommitAction
case object Delete extends TableCommitAction
case object Preserve extends TableCommitAction
case class CreateTable(tableScope: Option[String], tableName: String, tableContents: TableContentsSource, onCommit: Option[TableCommitAction]) extends SQLStatements