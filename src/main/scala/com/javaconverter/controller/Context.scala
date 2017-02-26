package com.javaconverter.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Context(val request: HttpServletRequest, val response: HttpServletResponse) {
  
  def get(route: String) : Boolean = matches(route, "GET")
  def post(route: String) : Boolean = matches(route, "POST")
   
  private def matches(route: String, method: String): Boolean = {
     request.getRequestURI.equals(route) && request.getMethod.equals(method)
  }
}