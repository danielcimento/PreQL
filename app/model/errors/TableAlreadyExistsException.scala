package model.errors

class TableAlreadyExistsException(schemaName: String, tableName: String) extends RuntimeException(s"Table already exists in the schema $schemaName with the name $tableName.")