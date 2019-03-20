package model.parse
import fastparse._, MultiLineWhitespace._

class SQLParser {

}

object SQLParser {
  def main(args: Array[String]): Unit = {
    def createTable[_: P] = P(IgnoreCase("create") ~ tableScope.!.? ~ IgnoreCase("table") ~ identifier.! ~ tableElementList ~ tableCommitAction.?).map(CreateTable.tupled)
    def tableScope[_: P] = P(("GLOBAL" | "LOCAL") ~ "TEMPORARY")
    def tableElementList[_: P]: P[TableContentsSource] = P("(" ~ tableElement.rep(1, sep = ",") ~ ")").map(TableElementList)
    def tableElement[_:P] = P(identifier.! ~ identifier.!).map(ColumnDefinition.tupled)
    def tableCommitAction[_: P]: P[TableCommitAction] = P(
      IgnoreCase("ON") ~ IgnoreCase("COMMIT") ~
        (P(IgnoreCase("DELETE")).map(_ => Delete.asInstanceOf[TableCommitAction]) |
        P(IgnoreCase("PRESERVE")).map(_ => Preserve.asInstanceOf[TableCommitAction])) ~
      IgnoreCase("ROWS"))
    def identifier[_: P] = P(CharsWhileIn("A-Za-z_"))


    val result = parse("create TABLE foo (bar int, bazz int, buzz int)", createTable(_))
    result match {
      case Parsed.Success(_, _) => println(result)
      case fail @ Parsed.Failure(_, _, _) => println(fail.trace())
    }
  }
}