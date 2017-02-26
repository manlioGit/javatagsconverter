package com

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.javaconverter.controller.MainController
import com.javaconverter.controller.Context

class Servlet extends HttpServlet { 
   
  override def service(request: HttpServletRequest, response: HttpServletResponse){
    new MainController().execute(new Context(request, response))    
  }
}