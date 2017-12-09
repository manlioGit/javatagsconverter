package com.javaconverter.view
import  com.github.manliogit.javatags.lang.HtmlHelper._
import com.github.manliogit.javatags.element.Element

class Layout(path: String, content: String, converted: String, error: String) extends Element {
  
   def render() : String = {
     html5(attr("class -> full", "lang -> en"),
        head(
            meta(attr("charset -> utf-8")),
            meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
            meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
            meta(attr("name -> description", "content -> javatags converter: convert html in javatags language, Java HTML builder")),
            meta(attr("name -> author", "content -> manlioGit")),
            title("javatagsconverter"),
            link(attr("rel-> stylesheet").add("href", path + "/css/bootstrap.min.css")),
            link(attr("rel-> stylesheet").add("href", path + "/css/codemirror.css")),
            link(attr("rel-> stylesheet").add("href", path + "/css/full.css")),
            text("""<!--[if lt IE 9]>
                     <script src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js></script>
                     <script src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.jsi></script>
                    <![endif]-->""")            
        ),
        body(attr("style -> background: url(" + path + "/images/background.jpg) no-repeat center center fixed;"),
          nav(attr("class -> navbar navbar-inverse navbar-fixed-top", "role -> navigation"),
              div(attr("class -> container" ),
                  div(attr("class -> navbar-header"),
                      a(attr("class -> navbar-brand").add("href", path), "JavaTagsConverter")
                  ),
                  div(attr("class -> collapse navbar-collapse"),
                    ul(attr("class -> nav navbar-nav"),
                        li(
                          a(attr("href -> https://github.com/manlioGit/javatags", "target -> _blank"),"Online converter for JavaTags")
                        )
                     )
                  )
              )
          ),
          div(attr("class -> row"), br()),
            if(!error.isEmpty())
            div(attr("class -> row"),
              div(attr("class -> col-md-8 col-md-offset-2 alert alert-warning alert-dismissible","role -> alert"),
                button(attr("type -> button","class -> close","data-dismiss -> alert","aria-label -> Close"),
                  span(attr("aria-hidden -> true"),
                    text("×")
                  )
                ),
                strong(
                  text("Warning!")
                ),
                text(error)
              )
            ) else span(),
        	div(attr("class -> row"),
        	  div(attr("class -> col-md-4 col-md-offset-2 panel panel-default content"),
          		form(attr("method -> post").add("action", path),
            		 div(attr("class -> form-group"),	
            		       textarea(attr("id -> htmlText", "class -> form-control", "rows -> 20", "data-role -> none", "name -> content"), text(content)) 
            		   ),
            		   button(attr("type -> submit", "class -> btn btn-default"), "Submit")
          	    )
          	  ),
          	  div(attr("class -> col-md-4 panel panel-default content"),
          		  form(
              		 div(attr("class -> form-group"),	
              		   textarea(attr("id -> javaText","class -> form-control", "rows -> 20", "data-role -> none"), text(converted))
              		 ),
              		 button(attr("type -> submit", "class -> btn btn-default", "disabled -> disabled"), "...")
          	     )
        	    )
          ),
          script(attr().add("src", path + "/js/jquery.js")),
          script(attr().add("src", path + "/js/bootstrap.min.js")),
          script(attr().add("src", path + "/js/codemirror.js")),
          script(attr().add("src", path + "/js/xml.js")),
          script(attr().add("src", path + "/js/clike.js")),
          script(
              "var htmlEditor = CodeMirror.fromTextArea(document.getElementById('htmlText'), { " + 
              "  lineNumbers: true, " +
              "  mode: 'text/html' "  +
              "}); " +
              "htmlEditor.setSize('100%','650');"
          ),
          script(
              "var javaEditor = CodeMirror.fromTextArea(document.getElementById('javaText'), { " + 
              "  lineNumbers: true, "  +
              "  mode: 'text/x-java' " +
              "}); " +
              "javaEditor.setSize('100%','650');"
          )
        )
      ).render();
   }
}