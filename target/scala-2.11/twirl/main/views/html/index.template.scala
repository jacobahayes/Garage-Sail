
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
"""),_display_(/*12.2*/main("Garage Sail")/*12.21*/ {_display_(Seq[Any](format.raw/*12.23*/("""
    """),format.raw/*13.5*/("""<script type='text/javascript' src='"""),_display_(/*13.42*/routes/*13.48*/.Assets.versioned("js/index.js")),format.raw/*13.80*/("""'></script>
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <style>

        body """),format.raw/*17.14*/("""{"""),format.raw/*17.15*/("""
            """),format.raw/*18.13*/("""background-color: #0086b3;
            font-family: "Poiret One";
        """),format.raw/*20.9*/("""}"""),format.raw/*20.10*/("""

        """),format.raw/*22.9*/("""#password """),format.raw/*22.19*/("""{"""),format.raw/*22.20*/("""
            """),format.raw/*23.13*/("""-webkit-text-security: circle;
        """),format.raw/*24.9*/("""}"""),format.raw/*24.10*/("""

        """),format.raw/*26.9*/("""#title """),format.raw/*26.16*/("""{"""),format.raw/*26.17*/("""
            """),format.raw/*27.13*/("""text-align: center;
            color: #5cb85c;
            font-size: 140px;
        """),format.raw/*30.9*/("""}"""),format.raw/*30.10*/("""

        """),format.raw/*32.9*/("""#login_form """),format.raw/*32.21*/("""{"""),format.raw/*32.22*/("""
            """),format.raw/*33.13*/("""text-align: center;
            margin: auto;
            width: 200px;
        """),format.raw/*36.9*/("""}"""),format.raw/*36.10*/("""

        """),format.raw/*38.9*/("""#login_form input """),format.raw/*38.27*/("""{"""),format.raw/*38.28*/("""
            """),format.raw/*39.13*/("""width: 150px;
            margin: auto;
            padding: 10px;
            text-align: left;
        """),format.raw/*43.9*/("""}"""),format.raw/*43.10*/("""

        """),format.raw/*45.9*/("""#register """),format.raw/*45.19*/("""{"""),format.raw/*45.20*/("""
            """),format.raw/*46.13*/("""text-align: center;
        """),format.raw/*47.9*/("""}"""),format.raw/*47.10*/("""

        """),format.raw/*49.9*/("""button """),format.raw/*49.16*/("""{"""),format.raw/*49.17*/("""
            """),format.raw/*50.13*/("""/*background-color: #5cb85c;*/
            /*width: 175px;*/
            padding: 5px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;

            background-color: #0086b3;
            color: #5cb85c;
            border: 2px solid #5cb85c;
        """),format.raw/*63.9*/("""}"""),format.raw/*63.10*/("""

        """),format.raw/*65.9*/("""#space """),format.raw/*65.16*/("""{"""),format.raw/*65.17*/("""
            """),format.raw/*66.13*/("""height: 5px;
        """),format.raw/*67.9*/("""}"""),format.raw/*67.10*/("""


        """),format.raw/*70.9*/("""button:hover """),format.raw/*70.22*/("""{"""),format.raw/*70.23*/("""
            """),format.raw/*71.13*/("""background-color: #5cb85c; /* Green */
            color: white;
            box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
        """),format.raw/*74.9*/("""}"""),format.raw/*74.10*/("""

        """),format.raw/*76.9*/("""#cancel_button """),format.raw/*76.24*/("""{"""),format.raw/*76.25*/("""
            """),format.raw/*77.13*/("""width: 45px;
        """),format.raw/*78.9*/("""}"""),format.raw/*78.10*/("""

        """),format.raw/*80.9*/("""#login_button """),format.raw/*80.23*/("""{"""),format.raw/*80.24*/("""
            """),format.raw/*81.13*/("""width: 125px;
        """),format.raw/*82.9*/("""}"""),format.raw/*82.10*/("""


    """),format.raw/*85.5*/("""</style>


    <html>
        <body>
            <div>
                <h1 id="title">Garage Sail</h1>
            </div>

            <div id="login_form">
                <form action=""""),_display_(/*95.32*/routes/*95.38*/.HomeController.login()),format.raw/*95.61*/("""" method="post">
                    <input type="text" name="username" id="username" placeholder="username">

                    <input type="text" name="password" id="password" placeholder="password">

                    <div id="space">

                    </div>

                    <div id="buttons">
                        <button id="cancel_button">X</button>
                        <button id="login_button">Login</button>
                    </div>

                </form>
            </div>

            <div id="register">
                <p>Not Registered?<a href=""""),_display_(/*113.45*/routes/*113.51*/.HomeController.login),format.raw/*113.72*/("""" id="logo">Sign Up Here.</a></p>
            </div>
        </body>
    </html>

""")))}),format.raw/*118.2*/("""
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
                  DATE: Thu Jun 09 10:15:00 EDT 2016
                  SOURCE: /Users/christopherpaschal/Documents/tech4/summer/cs2340/garagesail/Garage_Sail/app/views/index.scala.html
                  HASH: 1e7949cf6e7461d9b29550a6e69da881a0d9dd38
                  MATRIX: 834->95|946->112|975->308|1003->310|1031->329|1071->331|1103->336|1167->373|1182->379|1235->411|1403->551|1432->552|1473->565|1574->639|1603->640|1640->650|1678->660|1707->661|1748->674|1814->713|1843->714|1880->724|1915->731|1944->732|1985->745|2098->831|2127->832|2164->842|2204->854|2233->855|2274->868|2381->948|2410->949|2447->959|2493->977|2522->978|2563->991|2695->1096|2724->1097|2761->1107|2799->1117|2828->1118|2869->1131|2924->1159|2953->1160|2990->1170|3025->1177|3054->1178|3095->1191|3554->1623|3583->1624|3620->1634|3655->1641|3684->1642|3725->1655|3773->1676|3802->1677|3840->1688|3881->1701|3910->1702|3951->1715|4139->1876|4168->1877|4205->1887|4248->1902|4277->1903|4318->1916|4366->1937|4395->1938|4432->1948|4474->1962|4503->1963|4544->1976|4593->1998|4622->1999|4656->2006|4871->2194|4886->2200|4930->2223|5543->2808|5559->2814|5602->2835|5716->2918
                  LINES: 30->5|35->5|37->11|38->12|38->12|38->12|39->13|39->13|39->13|39->13|43->17|43->17|44->18|46->20|46->20|48->22|48->22|48->22|49->23|50->24|50->24|52->26|52->26|52->26|53->27|56->30|56->30|58->32|58->32|58->32|59->33|62->36|62->36|64->38|64->38|64->38|65->39|69->43|69->43|71->45|71->45|71->45|72->46|73->47|73->47|75->49|75->49|75->49|76->50|89->63|89->63|91->65|91->65|91->65|92->66|93->67|93->67|96->70|96->70|96->70|97->71|100->74|100->74|102->76|102->76|102->76|103->77|104->78|104->78|106->80|106->80|106->80|107->81|108->82|108->82|111->85|121->95|121->95|121->95|139->113|139->113|139->113|144->118
                  -- GENERATED --
              */
          