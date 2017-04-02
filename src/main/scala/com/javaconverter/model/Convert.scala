package com.javaconverter.model

import org.jsoup.Jsoup
import org.jsoup.select.NodeVisitor
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import org.jsoup.nodes.Comment
import scala.collection.JavaConversions._
import org.jsoup.nodes.Element
import org.jsoup.nodes.DataNode

class Convert(html: String) {

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
  
  def toJavaTags() = {
    val doc = Jsoup.parse(html)
    var result = ""
    doc.child(0).traverse(new NodeVisitor() {
        override def head(node: Node, depth: Int) {
          result += render(node, depth)
          var attribute = node.attributes().asList().map { attr => s""""${attr.getKey} -> ${attr.getValue}"""" }
          if (!attribute.isEmpty && node.isInstanceOf[Element]) {
            result += s"""attr(${attribute.mkString(",")})"""
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
  
  def toHtmlFormat() = {
    Jsoup.parse(html).toString().
      replaceAll("<", "&lt;").
      replaceAll(">", "&gt;")
  }
}