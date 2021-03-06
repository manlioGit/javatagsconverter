package com.javaconverter.controller

import com.github.manliogit.javatags.element.Element

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Context(val request: HttpServletRequest, val response: HttpServletResponse) {
  
  def get(route: String) : Boolean = matches(route, "GET")
  def post(route: String) : Boolean = matches(route, "POST")

  def html_response(data: Element): Unit = {
    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")

    response.getWriter.write(data.render)
  }

  def by(key: String, default: String = "") = {
    if(request.getParameterMap.containsKey(key)) request.getParameter(key) else default
  }

  private def matches(route: String, method: String): Boolean = {
     request.getRequestURI.equals(route) && request.getMethod.equals(method)
  }
}