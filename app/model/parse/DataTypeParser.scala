package model.parse

import fastparse._
import MultiLineWhitespace._
import model._

object DataTypeParser {
  def dataType[_: P] = P(int | varchar | char)

  def int[_: P] = P(IgnoreCase("INT")).map(_ => INT.asInstanceOf[DataType])
  def varchar[_: P] = P(IgnoreCase("VARCHAR") ~/ "(" ~/ number ~/ ")").map(VARCHAR.apply(_).asInstanceOf[DataType])
  def char[_: P] = P(IgnoreCase("CHAR") ~/ "(" ~/ number ~/ ")").map(CHAR.apply(_).asInstanceOf[DataType])

  def number[_: P]: P[Int] = P( CharIn("0-9").rep(1).!.map(_.toInt) )
}
