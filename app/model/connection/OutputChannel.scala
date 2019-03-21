package model.connection

trait OutputChannel {
  def sendOutput(): Unit
}

// Dummy output channel for testing
case object NullOutputChannel extends OutputChannel {
  override def sendOutput(): Unit = ()
}
