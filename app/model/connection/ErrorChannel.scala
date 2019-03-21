package model.connection

trait ErrorChannel {
  def sendError(e: Throwable): Unit
}

// Dummy object for testing purposes
case object NullErrorChannel extends ErrorChannel {
  override def sendError(e: Throwable): Unit = println(e.getMessage)
}
