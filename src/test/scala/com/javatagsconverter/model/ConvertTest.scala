package com.javatagsconverter.model

import com.javaconverter.model.Convert
import org.scalatest.FunSuite

class ConvertTest extends FunSuite {
   
  test("leaf element"){
    assert_render("""
      |html(
      |  head(),
      |  body(
      |    div()
      |  )
      |)""",
      "<div></div>"
    )
  }
  
  test("leaf text"){
    assert_render("""
      |html(
      |  head(),
      |  body(
      |    text("xxx")
      |  )
      |)""",
      "xxx"
    )
  }
  
  test("leaf comment"){
    assert_render("""
      |html(
      |  head(
      |    text("<!-- comment -->")
      |  ),
      |  body()
      |)""",
      "<head><!-- comment --></head>"
    )
  }
  
  test("leaf element with attribute"){
    assert_render("""
      |html(
      |  head(
      |    meta(attr("charset -> utf-8"))
      |  ),
      |  body()
      |)""",
      "<head><meta charset='utf-8'></head>"
    )
 }
  
  test("convert html to javatags") {
    assert_render("""
      |html(
      |  head(),
      |  body(
      |    div(
      |      text("xxx")
      |    )
      |  )
      |)""",
      "<html><head></head><body><div>xxx</div></body></html>"
    )
  }
  
  test("comments are managed as text node"){
    assert_render("""
      |html(
      |  head(),
      |  body(
      |    text("<!-- some comment -->"),
      |    div(
      |      text("xxx")
      |    )
      |  )
      |)""",
      "<body><!-- some comment --><div>xxx</div></body>"
    )
  }
  
  test("attributes"){
    assert_render("""
      |html(
      |  head(
      |    meta(attr("charset -> utf-8")),
      |    title(
      |      text("title")
      |    ),
      |    text("<!-- comment -->")
      |  ),
      |  body()
      |)""",
      "<head><meta charset='utf-8'><title>title</title><!-- comment --></head>"
    )
  }
  
  test("element with attribute and child"){
    assert_render("""
      |html(
      |  head(),
      |  body(attr("charset -> utf-8"),
      |    div()
      |  )
      |)""",
      "<body charset='utf-8'><div></div></body>"
    )
  }
  
  test("script content is managed as text node"){
    assert_render("""
      |html(
      |  head(
      |    script(
      |      text("console.log('hello');")
      |    )
      |  ),
      |  body()
      |)""",
      "<script>console.log('hello');</script>"
    )
  }

  test("empty"){
    assert_render("""
      |html(
      |  head(),
      |  body()
      |)""",
      ""
    )
  }

  private def assert_render(expected: String, actual: String): Unit = {
    assert(new Convert(actual).toTags == expected.stripMargin)
  }
}