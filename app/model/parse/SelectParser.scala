package model.parse

import fastparse._
import MultiLineWhitespace._
import model.statements.QueryDefinitions._
import IdentifierParser._
import model.statements.{SQLStatement, SelectStatement}

object SelectParser {
  def select[_: P] = P(IgnoreCase("select") ~ setQuantifier.? ~ selectList ~ tableExpression).map(SelectStatement.tupled(_).asInstanceOf[SQLStatement])

  def setQuantifier[_: P] = P(distinct | all)
  def distinct[_: P] = P(IgnoreCase("distinct")).map(_ => Distinct.asInstanceOf[SetQuantifier])
  def all[_ : P] = P(IgnoreCase("all")).map(_ => All.asInstanceOf[SetQuantifier])

  def selectList[_: P] = P(asterisk | qualifiedSelections)
  def asterisk[_ : P] = P("*").map(_ => Asterisk.asInstanceOf[SelectList])
  def qualifiedSelections[_ :P] = P(selectSublist.rep(min = 1, sep = ",")).map(QualifiedSelections)

  def selectSublist[_: P]: P[SelectSublist] = P(identifier.! ~ IgnoreCase("as") ~ identifier.!).map(DerivedColumn.tupled(_).asInstanceOf[SelectSublist])

  def tableExpression[_: P] = P(IgnoreCase("from") ~ identifier.!.rep(min = 1, sep = ",")).map(names => TableExpression(FromClause(names)))
}
