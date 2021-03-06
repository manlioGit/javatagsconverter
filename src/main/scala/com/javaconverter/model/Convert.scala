package com.javaconverter.model

import org.jsoup.Jsoup
import org.jsoup.nodes._
import org.jsoup.select.NodeVisitor

import scala.collection.JavaConversions._

class Convert(html: String = "", language: String = "java") {

  def toHtml() = {
    Jsoup.parse(html).toString().
      replaceAll("<", "&lt;").
      replaceAll(">", "&gt;")
  }

  def toTags() = {
    val attrMap = Map("java" -> javaAttribute _, "ruby" -> rubyAttribute _).withDefaultValue(javaAttribute _)

    val doc = Jsoup.parse(html)
    var result = ""
    doc.child(0).traverse(new NodeVisitor() {
        override def head(node: Node, depth: Int) {
          result += render(node, depth)
          val attribute = node.attributes().asList().map(attrMap(language))
          if (!attribute.isEmpty && node.isInstanceOf[Element]) {
            result += s"""attr(${attribute.mkString(", ")})"""
            if(!node.childNodes().isEmpty()){
              result += ","
            }
          }
        }

        override def tail(node: Node, depth: Int) {
          if(node.childNodes().isEmpty()){
            result +=  ")"
          } else{
            result +=   "\n" + pad(depth until 0 by -1) + ")"
          }
          if(node.nextSibling() != null){
            result += ","
          }
        }
      }
    )
    result
  }

  private def pad(r: Range) = r.map(_ => "  ").mkString

  private def render(node: Node, depth: Int) = {
    val render = node match {
      case n: Element => node.nodeName + "("
      case n: TextNode => s"""text("${node.asInstanceOf[TextNode].text()}""""
      case n: Comment => s"""text("<!--${node.asInstanceOf[Comment].getData}-->""""
      case n: DataNode => s"""text("${node.asInstanceOf[DataNode].getWholeData}""""
    }
    "\n" + pad(0 until depth) + render
  }

  private def rubyAttribute(attribute: Attribute) = {
    val key = if(attribute.getKey.contains("-")) s"'${attribute.getKey}'" else attribute.getKey
    s"""${key}: "${attribute.getValue}""""
  }

  private def javaAttribute(attribute: Attribute) = {
    s""""${attribute.getKey} -> ${attribute.getValue}""""
  }
}