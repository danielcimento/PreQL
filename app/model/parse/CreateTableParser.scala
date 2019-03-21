package model.parse

import fastparse._
import MultiLineWhitespace._
import model.statements.{CreateTableStatement, SQLStatement}
import model.statements.TableDefinitions._
import DataTypeParser._
import IdentifierParser._

object CreateTableParser {
  def createTable[_: P] =
    P(IgnoreCase("create") ~ tableScope.!.? ~ IgnoreCase("table") ~ identifier.! ~ tableElementList ~ tableCommitAction.?)
      .map(CreateTableStatement.tupled(_).asInstanceOf[SQLStatement])

  def tableScope[_: P] = P(("GLOBAL" | "LOCAL") ~ "TEMPORARY")

  def tableElementList[_: P]: P[TableContentsSource] = P("(" ~ tableElement.rep(1, sep = ",") ~ ")").map(TableElementList)
  def tableElement[_:P] = P(identifier.! ~ dataType).map(ColumnDefinition.tupled)

  def tableCommitAction[_: P]: P[TableCommitAction] = P(
    IgnoreCase("ON") ~ IgnoreCase("COMMIT") ~
      (P(IgnoreCase("DELETE")).map(_ => Delete.asInstanceOf[TableCommitAction]) |
        P(IgnoreCase("PRESERVE")).map(_ => Preserve.asInstanceOf[TableCommitAction])) ~
      IgnoreCase("ROWS"))
}
