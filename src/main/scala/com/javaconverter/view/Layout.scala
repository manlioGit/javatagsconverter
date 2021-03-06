package com.javaconverter.view
import com.github.manliogit.javatags.lang.HtmlHelper.{link, _}
import com.github.manliogit.javatags.element.Element
import com.javaconverter.model.Convert

class Layout(convert: Convert, error: String = "") extends Element {
  
   def render() : String = {
     html5(attr("class -> full", "lang -> en"),
       head(
         meta(attr("charset -> utf-8")),
         meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
         meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
         meta(attr("name -> description", "content -> javatags converter: convert html in javatags language, Java HTML builder")),
         meta(attr("name -> author", "content -> manlioGit")),
         title("javatagsconverter"),
         link(attr("rel-> stylesheet", "href -> /css/bootstrap.min.css")),
         link(attr("rel-> stylesheet", "href -> /css/codemirror-5.5.9.css")),
         link(attr("rel-> stylesheet", "href -> /css/full.css")),
         text("<!-- <a href='https://dryicons.com/free-icons/programming-language'> Icon by Dryicons </a>  -->"),
         link(attr("rel -> icon", "href -> /images/tags-favicon.svg")),
         text(
           """<!--[if lt IE 9]>
               <script src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js></script>
               <script src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.jsi></script>
              <![endif]-->
           """
         )
       ),
       body(attr("style -> background: url(/images/background.jpg) no-repeat center center fixed;"),
         div(attr("class -> container-fluid"),
           nav(attr("class -> navbar navbar-inverse navbar-fixed-top", "role -> navigation"),
             div(attr("class -> container" ),
               div(attr("class -> navbar-header"),
                 a(attr("class -> navbar-brand", "href -> /", "style -> padding-top: 0.475em;"), "Java/RubyTagsConverter")
               ),
               div(attr("class -> collapse navbar-collapse"),
                 ul(attr("class -> nav navbar-nav"),
                   li(
                     a(attr("href -> https://github.com/manlioGit/javatags", "target -> _blank"),"Online converter for JavaTags")
                   ),
                   li(
                     a(attr("href -> https://github.com/manlioGit/ruby-tags", "target -> _blank"),"Online converter for Ruby-Tags")
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
       	     div(attr("class -> col-md-5 col-md-offset-1 panel panel-default content"),
               form(attr("method -> post"),
            	   div(attr("class -> form-group"),
                   textarea(attr("id -> htmlText", "class -> form-control", "rows -> 20", "data-role -> none", "name -> content"),
                     text(convert.toHtml())
                   )
                 ),
                 button(attr("type -> submit", "class -> btn btn-default", "formaction -> /?type=java"), "JavaTags"),
                 button(attr("type -> submit", "class -> btn btn-default", "formaction -> /?type=ruby"), "RubyTags")
               )
             ),
             div(attr("class -> col-md-5 panel panel-default content"),
               form(
                 div(attr("class -> form-group"),
                   textarea(attr("id -> javaText","class -> form-control", "rows -> 20", "data-role -> none"),
                     text(convert.toTags())
                   )
                 ),
                 button(attr("type -> submit", "class -> btn btn-default", "disabled -> disabled"), "...")
               )
             )
           )
         ),
         script(attr().add("src", "/js/jquery.js")),
         script(attr().add("src", "/js/bootstrap.min.js")),
         script(attr().add("src", "/js/codemirror-5.5.9.js")),
         script(attr().add("src", "/js/ruby.js")),
         script(attr().add("src", "/js/xml.js")),
         script(attr().add("src", "/js/clike.js")),
         script(
           "var htmlEditor = CodeMirror.fromTextArea(document.getElementById('htmlText'), { " +
             "  lineNumbers: true, " +
             "  mode: 'text/html' "  +
             "}); " +
             "htmlEditor.setSize('100%','650');"
         ),
         script(
           "var editor = CodeMirror.fromTextArea(document.getElementById('javaText'), { " +
             "  lineNumbers: true, "  +
             "  mode: 'text/x-ruby' " +
             "}); " +
             "editor.setSize('100%','650');"
         )
       )
     ).render();
   }
}