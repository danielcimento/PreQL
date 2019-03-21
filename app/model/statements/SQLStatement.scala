package model.statements

import model.connection.{ErrorChannel, OutputChannel}
import model.entities.Database

trait SQLStatement {
  def execute(database: Database)(implicit oc: OutputChannel, ec: ErrorChannel): Database
}