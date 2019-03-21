package model.parse

import fastparse._, MultiLineWhitespace._

object IdentifierParser {
  def identifier[_: P] = P(CharsWhileIn("A-Za-z_"))
}
