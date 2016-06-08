
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*11.4*/("""
"""),_display_(/*12.2*/main("Welcome to Play")/*12.25*/ {_display_(Seq[Any](format.raw/*12.27*/("""
    """),format.raw/*13.5*/("""<script type='text/javascript' src='"""),_display_(/*13.42*/routes/*13.48*/.Assets.versioned("js/index.js")),format.raw/*13.80*/("""'></script>
    <form action=""""),_display_(/*14.20*/routes/*14.26*/.HomeController.addPerson()),format.raw/*14.53*/("""" method = "post">

        Username:
        <input type="text" name="username"></input>

            Password:
        <input type="text" name="password">

        <button>Login</button>
        
    </form>
""")))}),format.raw/*25.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/*
 * This template takes a single argument, a String containing a
 * message to display.
 */
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Wed Jun 08 18:31:56 EDT 2016
                  SOURCE: /Users/christopherpaschal/Documents/tech4/summer/cs2340/garagesail/Garage_Sail/app/views/index.scala.html
                  HASH: 31b8045533f4f99504cb97b91ef8f26036043417
                  MATRIX: 834->95|946->112|975->308|1003->310|1035->333|1075->335|1107->340|1171->377|1186->383|1239->415|1297->446|1312->452|1360->479|1601->690
                  LINES: 30->5|35->5|37->11|38->12|38->12|38->12|39->13|39->13|39->13|39->13|40->14|40->14|40->14|51->25
                  -- GENERATED --
              */
          