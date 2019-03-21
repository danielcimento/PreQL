package model.parse

import fastparse._, MultiLineWhitespace._
import CreateTableParser._
import SelectParser._

class SQLParser {

}

object SQLParser {
  def statementParser[_: P] = P((createTable | select) ~/ ";")

  def main(args: Array[String]): Unit = {
    val statement = "select * from table, table_two, table_three;"

    val result = parse(statement, statementParser(_))
    result match {
      case Parsed.Success(_, _) => println(result)
      case fail @ Parsed.Failure(_, _, _) => println(fail.trace())
    }
  }
}