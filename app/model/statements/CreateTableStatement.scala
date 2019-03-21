package model.statements

import TableDefinitions._
import model.DataType
import model.connection.{ErrorChannel, OutputChannel}
import model.entities.{Column, Database, Table}
import model.errors._
import model.utils.PageNameGenerator

case class CreateTableStatement(tableScope: Option[String], tableName: String, tableContents: TableContentsSource, onCommit: Option[TableCommitAction]) extends SQLStatement {
  override def execute(database: Database)(implicit oc: OutputChannel, ec: ErrorChannel): Database = {
    val (schema, name) = tableName.split('.').toList.reverse match {
      case n :: s :: _ => (s.toUpperCase, n.toUpperCase)
      case n :: _ => ("PUBLIC", n.toUpperCase)
      case _ => throw new AssertionError("Parser allowed a CREATE TABLE statement with an invalid table name")
    }

    database.schemas.get(schema) match {
      case None =>
        ec.sendError(new SchemaNotFoundException(schema))
        database

      case Some(s) => s.tables.get(name) match {
        case Some(_) =>
          ec.sendError(new TableAlreadyExistsException(schema, name))
          database

        case None =>
          val columns: Seq[Column] = tableContents match {
            case TableElementList(elements) => elements.collect {
              case ColumnDefinition(cname, ctype) => Column(cname.toUpperCase, ctype)
            }
          }
          // TODO: Add column constraints and table constraints
          val newTable = Table(name, PageNameGenerator.newPageName, columns)

          // TODO: Create the actual page file,

          val updatedSchema = s.copy(tables = s.tables + (name -> newTable))
          database.copy(schemas = database.schemas + (schema -> updatedSchema))
      }
    }
  }
}

object TableDefinitions {
  // The possible set of elements in a table's definition
  trait TableElement
  case class ColumnDefinition(columnName: String, columnType: DataType) extends TableElement
  case class TableConstraintDefinition() extends TableElement
  //case class LikeClause() extends TableElement
  //case class SelfReferencingColumnSpecification() extends TableElement
  //case class ColumnOptions() extends TableElement

  // The possible set of definitions from which a table can be created
  trait TableContentsSource
  case class TableElementList(elements: Seq[TableElement]) extends TableContentsSource
  case class TypedTableSource(compositeType: String, constraints: Map[String, ColumnContstraint], tableConstraints: Seq[TableConstraintDefinition]) extends TableContentsSource

  trait ColumnContstraint

  // The possible set of actions that can be performed when a table is committed
  trait TableCommitAction
  case object Delete extends TableCommitAction
  case object Preserve extends TableCommitAction
}





