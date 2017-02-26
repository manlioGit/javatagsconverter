package com.javaconverter.view
import  com.github.manliogit.javatags.lang.HtmlHelper._
import com.github.manliogit.javatags.element.Element

class Layout(content: String, converted: String, error: String) {
  
   def build() : Element = {
     html5(attr("class -> full", "lang -> en"),
        head(
            meta(attr("charset -> utf-8")),
            meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
            meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
            meta(attr("name -> description", "content -> javatags converter: convert html in javatags language")),
            meta(attr("name -> author", "content -> manlioGit")),
            title("javatagsconverter"),
            link(attr("href -> css/bootstrap.min.css", "rel-> stylesheet")),
            link(attr("href -> css/full.css", "rel-> stylesheet")),
            text("""<!--[if lt IE 9]>
                     <script src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js></script>
                     <script src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.jsi></script>
                    <![endif]-->""")            
        ),
        body(
          nav(attr("class -> navbar navbar-inverse navbar-fixed-top", "role -> navigation"),
              div(attr("class -> container" ),
                  div(attr("class -> navbar-header"),
                      a(attr("class -> navbar-brand", "href -> /" ), "JavaTagsConverter")
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
          		form(attr("method -> post", "action -> /" ),
            		 div(attr("class -> form-group"),	
            		       textarea(attr("class -> form-control", "rows -> 20", "data-role -> none", "name -> content"), text(content)) 
            		   ),
            		   button(attr("type -> submit", "class -> btn btn-default"), "Submit")
          	    )
          	  ),
          	  div(attr("class -> col-md-4 panel panel-default content"),
          		  form(
              		 div(attr("class -> form-group"),	
              		   textarea(attr("class -> form-control", "rows -> 20", "data-role -> none"), text(converted))
              		 ),
              		 button(attr("type -> submit", "class -> btn btn-default", "disabled -> disabled"), "...")
          	     )
        	    )
          ),
          script(attr("src -> js/jquery.js")),
          script(attr("src -> js/bootstrap.min.js"))
        )
      );
   }
}