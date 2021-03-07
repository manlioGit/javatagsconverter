package com.javaconverter.controller

import com.github.manliogit.javatags.element.Element

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Context(val request: HttpServletRequest, val response: HttpServletResponse) {

  def html_response(data: Element): Unit = {
    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")

    response.getWriter.write(data.render)
  }

  def by(key: String, default: String = "") = {
    if(request.getParameterMap.containsKey(key)) request.getParameter(key) else default
  }
}