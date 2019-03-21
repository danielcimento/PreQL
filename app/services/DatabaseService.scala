package services

import model.entities.Database
import fastparse._
import model.connection.{NullErrorChannel, NullOutputChannel}
import model.parse.SQLParser

class DatabaseService() {
  private var _canonicalDatabase = loadInitialDatabase()
  def canonicalDatabase: Database = _canonicalDatabase

  private def loadInitialDatabase(): Database = {
    // TODO: Pull the known database schemata from a file at startup
    Database.emptyDatabase("PRE_QL")
  }

  def parseAndExecuteStatement(rawStatement: String): Unit = {
    parse(rawStatement, SQLParser.statementParser(_)) match {
      case Parsed.Success(parsedStatement, _) =>
        // TODO: Add error and output handling
        // TODO: Add authorization and execution permissions
        _canonicalDatabase = parsedStatement.execute(canonicalDatabase)(NullOutputChannel, NullErrorChannel)
      case Parsed.Failure(_, _, _) => // TODO: Error handling (+ custom syntax error messages)
    }
  }
}
