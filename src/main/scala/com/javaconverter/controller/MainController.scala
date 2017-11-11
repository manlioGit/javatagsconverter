package com.javaconverter.controller
import  com.github.manliogit.javatags.lang.HtmlHelper._
import org.jsoup.Jsoup
import com.javaconverter.view.Layout
import com.javaconverter.model.Convert


class MainController extends Controller {
	val ROUTE = "/"

	override def handles(route: String): Boolean = route.equals(ROUTE)

	override def execute(context: Context) {
		context.response.setContentType("text/html")
		context.response.setCharacterEncoding("UTF-8")

		val content = context.request.getParameter("content") match {
		      case c: String => c.split("\n").filter(!_.isEmpty).map(_.trim).mkString
		      case _ => ""
		}
		
		var javatags = ""
  	var htmlFormat = ""
  	var error = ""
		try{
  		javatags = new Convert(content).toHtmlFormat() 
  		htmlFormat = new Convert(content).toJavaTags()
		} catch {
		  case _: Exception => error = " Probably your input It's not a valid html!"
		}
		context.response.getWriter.write(
		    new Layout(
		        context.request.getContextPath,
		        javatags, 
		        htmlFormat,
		        error
        ).render()  
     )
  }
}