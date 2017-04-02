package com.javatagsconverter.model

import org.scalatest.FunSuite
import org.jsoup.Jsoup
import org.jsoup.select.NodeVisitor
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import scala.collection.JavaConversions._
import org.jsoup.nodes.Comment
import com.javaconverter.model.Convert
import com.github.manliogit.javatags.element.Element
import  com.github.manliogit.javatags.lang.HtmlHelper._

class ConvertTest extends FunSuite {
   
  test("leaf element"){
    val expected =  """|
                             |html(
                             |  head(),
                             |  body(
                             |    div()
                             |  )
                             |)""".stripMargin
                             
      val result = new Convert("<div></div>").toJavaTags()
      
      assert(result == expected)
  }
  
  test("leaf text"){
    val expected =  """|
                             |html(
                             |  head(),
                             |  body(
                             |    text("xxx")
                             |  )
                             |)""".stripMargin
                             
      val result = new Convert("xxx").toJavaTags()
      
      assert(result == expected)
  }
  
  test("leaf comment"){
        val expected =  """|
                                 |html(
                                 |  head(
                                 |    text("<!-- comment -->")
                                 |  ),
                                 |  body()
                                 |)""".stripMargin
                             
      val result = new Convert("<head><!-- comment --></head>").toJavaTags()

      assert(result == expected)
  }
  
    test("leaf element with attribute"){
        val expected =  """|
                                 |html(
                                 |  head(
                                 |    meta(attr("charset -> utf-8"))
                                 |  ),
                                 |  body()
                                 |)""".stripMargin
                             
      val result = new Convert("<head><meta charset='utf-8'></head>").toJavaTags()

      assert(result == expected)
  }
  
  test("convert html to javatags") {
    val expected =  """|
                             |html(
                             |  head(),
                             |  body(
                             |    div(
                             |      text("xxx")
                             |    )
                             |  )
                             |)""".stripMargin
     val result = new Convert("<html><head></head><body><div>xxx</div></body></html>").toJavaTags()
     
     assert(result == expected)
  }
  
  test("comments are managed as text node"){
    val expected =  """|
                             |html(
                             |  head(),
                             |  body(
                             |    text("<!-- some comment -->"),
                             |    div(
                             |      text("xxx")
                             |    )
                             |  )
                             |)""".stripMargin
     val result = new Convert("<body><!-- some comment --><div>xxx</div></body>").toJavaTags()
     
     assert(result == expected)
  }
  
  test("attributes"){
    val expected =  """|
                             |html(
                             |  head(
                             |    meta(attr("charset -> utf-8")),
                             |    title(
                             |      text("title")
                             |    ),
                             |    text("<!-- comment -->")
                             |  ),
                             |  body()
                             |)""".stripMargin
     val result = new Convert("<head><meta charset='utf-8'><title>title</title><!-- comment --></head>").toJavaTags()
     
     assert(result == expected)
  }
  
  test("element with attribute and child"){
        val expected =  """|
                                 |html(
                                 |  head(),
                                 |  body(attr("charset -> utf-8"),
                                 |    div()
                                 |  )
                                 |)""".stripMargin
                             
        val result = new Convert("<body charset='utf-8'><div></div></body>").toJavaTags()

        assert(result == expected)
  }
  
  test("script content is managed as text node"){
        val expected =  """|
                                 |html(
                                 |  head(
                                 |    script(
                                 |      text("console.log('hello');")
                                 |    )
                                 |  ),
                                 |  body()
                                 |)""".stripMargin
    
        val result = new Convert("<script>console.log('hello');</script>").toJavaTags()
    
       assert(result == expected)
  }
}