package model.parse

import fastparse._
import MultiLineWhitespace._
import CreateTableParser._
import SelectParser._
import model.connection.{NullErrorChannel, NullOutputChannel}
import model.entities.Database
import model.statements.SQLStatement

class SQLParser {

}

object SQLParser {
  def statementParser[_: P]: P[SQLStatement] = P((createTable | select) ~/ ";")

  def main(args: Array[String]): Unit = {
    val statementText = "create table goof (id int, name varchar(32), password_hash char(60));"

    val result: Parsed[SQLStatement] = parse(statementText, statementParser(_))
    result match {
      case Parsed.Success(statement, _) =>
        println(statement)
        println(statement.execute(Database.emptyDatabase("test"))(NullOutputChannel, NullErrorChannel))
      case fail @ Parsed.Failure(_, _, _) => println(fail.trace())
    }
  }
}