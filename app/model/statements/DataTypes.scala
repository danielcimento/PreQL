package model.statements

trait DataType {
  // Size of the data type in bytes
  val size: Int
}

case object INT extends DataType {
  override val size: Int = 4
}

case class VARCHAR(size: Int) extends DataType

case class CHAR(size: Int) extends DataType