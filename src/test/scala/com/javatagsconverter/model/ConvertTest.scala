package com.javatagsconverter.model

import com.javaconverter.model.Convert
import org.scalatest.FunSuite

class ConvertTest extends FunSuite {

  test("empty"){
    assert_render("""
      |html5(
      |  head(),
      |  body()
      |)""",
      ""
    )
  }

  test("leaf element"){
    assert_render("""
      |html5(
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
      |html5(
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
      |html5(
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
      |html5(
      |  head(
      |    meta(attr("charset -> utf-8"))
      |  ),
      |  body()
      |)""",
      "<head><meta charset='utf-8'></head>"
    )
  }

  test("leaf element with attribute ruby"){
    assert_render("""
      |html5(
      |  head(
      |    meta(attr('http-equiv': "X-UA-Compatible"))
      |  ),
      |  body()
      |)""",
      "<head><meta http-equiv='X-UA-Compatible'></head>",
      "ruby"
    )
  }
  
  test("convert html to javatags") {
    assert_render("""
      |html5(
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
      |html5(
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
      |html5(
      |  head(
      |    meta(attr("charset -> utf-8", "class -> something x")),
      |    title(
      |      text("title")
      |    ),
      |    text("<!-- comment -->")
      |  ),
      |  body()
      |)""",
      "<head><meta charset='utf-8' class='something x'><title>title</title><!-- comment --></head>"
    )
  }

  test("attributes ruby"){
    assert_render("""
      |html5(
      |  head(
      |    meta(attr(charset: "utf-8", class: "something x")),
      |    title(
      |      text("title")
      |    ),
      |    text("<!-- comment -->")
      |  ),
      |  body()
      |)""",
      "<head><meta charset='utf-8' class='something x'><title>title</title><!-- comment --></head>",
      "ruby"
    )
  }

  test("attributes python"){
    assert_render("""
      |html5(
      |  head(
      |    meta(attr({'charset': "utf-8", 'class': "something x"})),
      |    title(
      |      text("title")
      |    ),
      |    text("<!-- comment -->")
      |  ),
      |  body()
      |)""",
      "<head><meta charset='utf-8' class='something x'><title>title</title><!-- comment --></head>",
      "python"
    )
  }
  
  test("element with attribute and child"){
    assert_render("""
      |html5(
      |  head(),
      |  body(attr("charset -> utf-8"),
      |    div()
      |  )
      |)""",
      "<body charset='utf-8'><div></div></body>"
    )
  }

  test("element with attribute and child ruby"){
    assert_render("""
      |html5(
      |  head(),
      |  body(attr(charset: "utf-8"),
      |    div()
      |  )
      |)""",
      "<body charset='utf-8'><div></div></body>",
      "ruby"
    )
  }

  test("element with attribute and child python"){
    assert_render("""
      |html5(
      |  head(),
      |  body(attr({'charset': "utf-8"}),
      |    div()
      |  )
      |)""",
      "<body charset='utf-8'><div></div></body>",
      "python"
    )
  }
  
  test("script content is managed as text node"){
    assert_render("""
      |html5(
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

  test("language") {
    assert(new Convert().language ==  "java")
    assert(new Convert(language = "ruby").language ==  "ruby")
    assert(new Convert(language = "python").language ==  "python")
  }

  private def assert_render(expected: String, actual: String, language: String = "java"): Unit = {
    assert(new Convert(actual, language).toTags == expected.stripMargin)
  }
}