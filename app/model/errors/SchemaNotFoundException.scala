package model.errors

class SchemaNotFoundException(missingSchema: String) extends RuntimeException(s"Could not find the schema $missingSchema.")