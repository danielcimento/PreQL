package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{JsLookupResult, JsSuccess, JsValue}
import play.api.mvc._
import play.libs.Json
import services.DatabaseService
import play.api.{Logger}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class DatabaseController @Inject()(cc: ControllerComponents, dbService: DatabaseService) extends AbstractController(cc) {
  val log = Logger("DatabaseController")
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def statement() = Action(parse.json) { implicit request: Request[JsValue] =>
    (request.body \ "statement").validate[String] match {
      case JsSuccess(statement, _) =>
        dbService.parseAndExecuteStatement(statement)
        Ok(dbService.canonicalDatabase.toString)
      case _ =>
        log.warn("No statement")
        NoContent
    }
  }
}
