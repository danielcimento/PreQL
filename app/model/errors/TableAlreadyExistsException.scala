package model.errors

class TableAlreadyExistsException(tableName: String) extends RuntimeException(s"Table already exists with the name $tableName.")