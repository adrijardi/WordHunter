package controllers

import play.api.libs.json.Json
import play.api.mvc._
import wordhunter.{Hunter, WordRegex}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def results(query: String) = Action {
    Ok(Json.obj("result" -> Hunter.lookup("myTest.dict", WordRegex(query))))
  }

}