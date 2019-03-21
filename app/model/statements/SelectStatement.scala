package model.statements

import QueryDefinitions._

case class SelectStatement(setQuantifier: Option[SetQuantifier], selectList: SelectList, tableExpression: TableExpression) extends SQLStatement

object SelectDefinitions {}


object QueryDefinitions {
  trait SetQuantifier
    case object Distinct extends SetQuantifier
    case object All extends SetQuantifier

  trait SelectList
    case object Asterisk extends SelectList
    case class QualifiedSelections(elements: Seq[SelectSublist]) extends SelectList

  trait SelectSublist
    // TODO: Add value expressions for selections
    case class DerivedColumn(columnName: String, columnAlias: String) extends SelectSublist
    trait QualifiedAsterisk extends SelectSublist
      case class AsteriskedIdentifierChain(identifiers: Seq[String]) extends QualifiedAsterisk
//      case class AllFieldsReference() extends QualifiedAsterisk

  // TODO: Add where, group by, having, and window
  case class TableExpression(fromClause: FromClause)
  case class FromClause(tableReferences: Seq[String])
}
