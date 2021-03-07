package com.javaconverter.controller
import com.javaconverter.model.Convert
import com.javaconverter.view.Layout


class MainController extends Controller {
  val ROUTE = "/"

  override def handles(route: String): Boolean = route.equals(ROUTE)

  override def execute(context: Context) {

    val content = context.by("content").split("\n").filter(!_.isEmpty).map(_.trim).mkString

    try{
      context.html_response(
        new Layout(new Convert(content, context.by("type")))
      )
    } catch {
      case _: Exception => {
        context.html_response(
          new Layout(new Convert, " Probably your input is not a valid html!")
        )
      }
    }
  }
}