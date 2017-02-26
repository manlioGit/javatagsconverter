package com.javaconverter.controller

trait Controller {
  def  handles(route: String) : Boolean
  def  execute(context: Context) 
}